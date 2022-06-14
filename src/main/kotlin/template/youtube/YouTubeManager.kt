package template.youtube

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpRequest
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.SearchListResponse
import com.google.api.services.youtube.model.SearchResult
import com.kotlindiscord.kord.extensions.utils.env
import java.io.IOException
import java.security.GeneralSecurityException

object YouTubeManager {
    @Throws(GeneralSecurityException::class, IOException::class)
    fun searchVideo(key: String?): String {
        val youtube = YouTube.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory()
        ) { request: HttpRequest? -> }.setApplicationName("mirko").build()
        val apiKey = env("YOUTUBE_API_KEY")
        val search =
            youtube.search().list(listOf("id,snippet"))
        search.key = apiKey
        search.q = "MrWissen2go $key"
        search.maxResults = 10L
        val searchResponse: SearchListResponse
        searchResponse = search.execute()
        val searchResultList =
            searchResponse.items
        val iteratorSearch: Iterator<SearchResult> =
            searchResultList.iterator()
        val singleVideo = iteratorSearch.next()
        val rID = singleVideo.id
        return "https://www.youtube.com/watch?v=" + rID.videoId
    }
}