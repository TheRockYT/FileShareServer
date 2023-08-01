package therockyt.fileshareserver

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import java.io.File

class RootHandler : HttpHandler {
    override fun handle(exchange: HttpExchange) {

        val hostString = exchange.remoteAddress.hostName

        val responseBody = exchange.responseBody

        val path = exchange.requestURI.path
        val cookiesString = exchange.requestHeaders.getFirst("Cookie")

        val cookies = HashMap<String, String>()
        if (cookiesString != null) {
            val cookieSplit = cookiesString.split("; ")
            for (currentString in cookieSplit) {
                val currentStringSplit = currentString.split("=")
                val key = currentStringSplit[0]
                val value = currentStringSplit[1]
                cookies[key] = value
            }
        }

        if (!isPasswordProtected || cookies["password"].equals(password)) {
            println("Requested file $path from $hostString")
            val requestedFile = File(resourceFolder, path)
            if (requestedFile.exists()) {
                if (requestedFile.isDirectory) {
                    var list = "<ul>"
                    var linkPath = path
                    if (!path.endsWith("/")) {
                        linkPath += "/"
                    }
                    if (!linkPath.equals("/")) {
                        list += "<li><a href=\"$linkPath../\">..</a></li>"
                    }
                    for (file in requestedFile.listFiles()!!) {
                        list += "<li><a href=\"$linkPath${file.name}\">${file.name}</a></li>"
                    }
                    list += "</ul>"
                    val response =
                        indexHtml!!.replace("%name%", requestedFile.path).replace("%list%", list).toByteArray()
                    exchange.sendResponseHeaders(200, response.size.toLong())
                    responseBody.write(response)
                } else {
                    val response = requestedFile.readBytes()
                    exchange.sendResponseHeaders(200, response.size.toLong())
                    responseBody.write(response)
                }
            } else {
                val response = notFoundHtml!!.toByteArray()
                exchange.sendResponseHeaders(404, response.size.toLong())
                responseBody.write(response)
            }
        } else {
            val password = cookies["password"]
            if (!password.isNullOrEmpty()) {
                println("Requested auth page from $hostString")
                val response = wrongAuthHtml!!.toByteArray()
                exchange.sendResponseHeaders(418, response.size.toLong())
                responseBody.write(response)
            } else {
                println("Wrong password request from $hostString")
                val response = authHtml!!.toByteArray()
                exchange.sendResponseHeaders(200, response.size.toLong())
                responseBody.write(response)
            }
        }
        responseBody.flush()
        responseBody.close()
        exchange.close()
    }
}
