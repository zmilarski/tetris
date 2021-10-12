package sample;

// A Java program for a Client
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Client extends GameTetris
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input  = new DataInputStream(System.in);

            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "Tetris" is input
        while (!line.equals("Tetris"))
        {
            try
            {
                //line = input.readLine();
                Thread.sleep(2000);

                out.writeUTF(TITLE_OF_PROGRAM);

                /*input.close();
                out.close();*/
                // socket.close();
                break;

            }
            catch(IOException | InterruptedException i)
            {

                System.out.println(i);
            }
        }

        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}