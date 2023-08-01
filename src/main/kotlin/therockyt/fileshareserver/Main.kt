package therockyt.fileshareserver

import com.sun.net.httpserver.HttpServer
import java.io.File
import java.net.InetSocketAddress

var port = 8081
var password = "-"
var isPasswordProtected = false

var resourceFolder = File("/")

val resourceReader = ResourceReader()

val indexHtml = resourceReader.readResource("/index.html")
val authHtml = resourceReader.readResource("/auth.html")
val wrongAuthHtml = resourceReader.readResource("/wrong_auth.html")
val notFoundHtml = resourceReader.readResource("/not_found.html")

fun main(args: Array<String>) {
    println("Welcome to FileShareServer!")
    if (!args.contains("defaults")) {
        println("What is the port of the Web-Server? (Integer)")
        print("PORT: ")
        port = readln().toInt()
        println("Should the Web-Server be password protected? (true / false)")
        println("(Recommended if you use it on public WiFi)")
        print("PROTECTED: ")
        isPasswordProtected = readln().toBoolean()
        if (isPasswordProtected) {
            println("What is the password of the Web-Server? (String)")
            print("PASSWORD: ")
            password = readln()
        }
        println("What is the path of the folder you want to share? (String)")
        print("PATH: ")
        resourceFolder = File(readln())
    }

    println("Starting Server...")
    val httpServer = HttpServer.create(InetSocketAddress(port), 0)

    httpServer.createContext("/", RootHandler())

    httpServer.executor = null
    httpServer.start()
    println("Server started successfully.")
}