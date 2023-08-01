# FileShareServer

## What is FileShareServer?

FileShareServer is a simple file sharing server written in Kotlin. It is designed to be used in a local network.

## Prerequisites

- Java 17 or higher installed on you host computer.

## How to use FileShareServer?

1. Download the latest release from the releases page.
2. Connect the computer you want to use as a server to the local network you want to use. (Mobile hotspots work too)
3. Run the jar file with `java -jar FileShareServer.jar`.
4. Configure the server using the cli. The setup assistant will guide you through the process.
5. Get the IP address of the server and open it in your browser on you other devices. (
   See [How to get the IP address of the computer?](#how-to-get-the-ip-address-of-the-computer))
    - Don't forget to add the port to the IP address. (e.g. `10.10.0.5:8080`)
6. Download the files you want to have on your other devices.

## How to get the IP address of the computer?

### Windows

1. Open the command prompt.
2. Type `ipconfig`.
3. Search for the IPv4 address of the network adapter you are using.

### Linux

1. Open the terminal.
2. Type `ip addr`.
3. Search for the IPv4 address of the network adapter you are using.

## Why FileShareServer?

I was on vacation and wanted to share some files with my other devices, both Android and Apple. I didn't want to use the
cloud because of the extremely slow and expensive internet connection. I also didn't want to use a USB stick because I
didn't have one with me. So I decided to write a simple file sharing server that I could run on my laptop and share
photos and videos with my other devices using a mobile hotspot without internet connection.