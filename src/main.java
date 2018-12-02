import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Scanner;

public class main {
    public static void main(String args[]){
        String host = "77.138.96.175";
        int port    = 5551;
        try {
            Socket socket = new Socket(host,port);
            DataInputStream inputStream   = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            File file = new File("./tree.jpg");
            System.out.println("choose action:\n 1)send image\n 2)send message");
            int action = scanner.nextInt();
            if (action == 1) {
                outputStream.write("image".getBytes());
                outputStream.write(Files.readAllBytes(file.toPath()));
                outputStream.write("sent".getBytes());
            } else if (action == 2) {
                outputStream.write("message".getBytes());
                System.out.println("enter message");
                String message = scanner.next();
                outputStream.write(message.getBytes());
            } else {
                System.out.println("invalid input ");
                return;
            }
            outputStream.flush();
            socket.close();
            outputStream.close();
            inputStream.close();
            //Thread sendThread = new Thread(new Runnable() {
            //    @Override
            //    public void run() {
            //        while (true) {
            //            String message = scanner.nextLine();
            //            try {
            //                outputStream.write(message.getBytes());
            //                outputStream.flush();
            //            } catch (IOException e) {
            //                e.printStackTrace();
            //            }
            //            if (message.equals("quit")) {
            //                return;
            //            }
            //        }

            //    }
            //});
            //Thread receiveThread = new Thread(new Runnable() {
            //    @Override
            //    public void run() {
            //        while (true) {
            //            byte[] buffer = new byte[1024];
            //            int bufferSize = 0;
            //            try {
            //                bufferSize = inputStream.read(buffer);
            //            } catch (IOException e) {
            //                e.printStackTrace();
            //            }
            //            String message = new String(buffer,0,bufferSize);
            //            System.out.println(message);
            //            if (message.contains("quit")) {
            //                return;
            //            }
            //        }

            //    }
            //});
            //sendThread.start();
            //receiveThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
