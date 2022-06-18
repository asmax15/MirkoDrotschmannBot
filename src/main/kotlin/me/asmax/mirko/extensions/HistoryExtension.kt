package me.asmax.mirko.extensions

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.chatCommand
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import com.kotlindiscord.kord.extensions.utils.respond
import dev.kord.common.annotation.KordPreview
import me.asmax.mirko.SERVER_ID
import me.asmax.mirko.facts.FactManager

@OptIn(KordPreview::class)
class HistoryExtension : Extension() {
    override val name: String = "history"

    override suspend fun setup() {
        chatCommand() {
            name = "history"
            description = "Frage Mirko Drotschmann nach einem zufälligem Geschichts-Fakt"

            check { failIf(event.message.author == null) }

            action {
                val kord = this@HistoryExtension.kord

                message.respond(FactManager.getRandomFact())
            }
        }

        publicSlashCommand() {
            name = "history"
            description = "Frage Mirko Drotschmann nach einem zufälligem Geschichts-Fakt"

            guild(SERVER_ID)

            action {
                val kord = this@HistoryExtension.kord

                respond {
                    content = FactManager.getRandomFact()
                }
            }
        }
    }
}