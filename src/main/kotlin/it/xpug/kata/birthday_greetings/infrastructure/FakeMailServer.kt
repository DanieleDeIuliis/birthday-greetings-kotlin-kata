package it.xpug.kata.birthday_greetings.infrastructure

import com.icegreen.greenmail.util.GreenMail
import com.icegreen.greenmail.util.GreenMailUtil
import com.icegreen.greenmail.util.ServerSetup
import java.io.Closeable
import javax.mail.Address

class FakeMailServer(port: Int) : Closeable {
    private val mailServer = GreenMail(ServerSetup(port, "0.0.0.0", ServerSetup.PROTOCOL_SMTP))

    fun start(): FakeMailServer {
        mailServer.start()
        return this
    }

    override fun close() {
        printAllMessages()
        mailServer.stop()
    }

    private fun printAllMessages() {
        mailServer.receivedMessages.forEach { message ->
            println(message.allRecipients.map(Address::toString))
            println(message.subject)
            println(GreenMailUtil.getBody(message))
            println("-------")
        }
    }
}