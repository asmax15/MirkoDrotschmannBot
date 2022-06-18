package me.asmax.mirko.extensions

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.converters.impl.coalescingDefaultingString
import com.kotlindiscord.kord.extensions.commands.converters.impl.defaultingString
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.chatCommand
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import com.kotlindiscord.kord.extensions.utils.respond
import me.asmax.mirko.SERVER_ID
import me.asmax.mirko.youtube.YouTubeManager

class MirkoSearchExtension : Extension() {
    override val name: String = "mirkosearch"

    override suspend fun setup() {
        chatCommand(MirkoSearchExtension::SearchArgs) {
            name = "mirkosearch"
            description = "Frage Mirko Drotschmann nach einem Video von ihm."

            check { failIf(event.message.author == null) }

            action {
                val kord = this@MirkoSearchExtension.kord

                message.respond(YouTubeManager.searchVideo(arguments.searchKey))
            }
        }

        publicSlashCommand(MirkoSearchExtension::SearchSlashArgs) {
            name = "mirkosearch"
            description = "Frage Mirko Drotschmann nach einem Video von ihm."

            guild(SERVER_ID)

            action {
                val kord = this@MirkoSearchExtension.kord

                respond {
                    content = YouTubeManager.searchVideo(arguments.searchKey)
                }
            }
        }
    }

    inner class SearchArgs : Arguments() {
        val searchKey by coalescingDefaultingString {
            name = "search key"

            defaultValue = "putin"
            description = "Das Wort nach dem auf Mirko's Kanal gesucht werden soll."
        }
    }

    inner class SearchSlashArgs : Arguments() {
        val searchKey by defaultingString {
            name = "searchKey"

            defaultValue = "putin"
            description = "Der Suchbegriff nach dem auf dem Kanal von Mirko gesucht werden soll."
        }
    }
}
