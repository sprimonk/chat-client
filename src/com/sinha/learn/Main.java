package com.sinha.learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try(Socket socket = new Socket("localhost",5000)){
            socket.setSoTimeout(5000);
            //to read data from server
            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //to write data to server
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(),true);
            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do{
                System.out.println("Enter String to echoe :");
                echoString = scanner.nextLine();

                //sending to server below
                stringToEcho.println(echoString);

                if (!echoString.equals("exit")){
                    //reading data from the server
                    response = echoes.readLine();
                    System.out.println(response);
                }
            }while (!echoString.equals("exit"));

        }catch (SocketTimeoutException e){
            System.out.println("Sccket timed out");
        }
        catch (IOException e){
            System.out.println("Client exception :" + e.getMessage());
        }
    }
}
