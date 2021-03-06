package cn.com.huangdc.network;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTCP2 {

    /**
     * 客户端：发送数据
     */
    @Test
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream is = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9000);
            outputStream = socket.getOutputStream();
            outputStream.write("客户端 发的消息呀".getBytes());
            // InputStream.read是阻塞式，需要客户端发送结束命令
            socket.shutdownOutput();

            is = socket.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 服务端：接收数据
     */
    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            serverSocket = new ServerSocket(9000);
            accept = serverSocket.accept();
            is = accept.getInputStream();

            byte[] b = new byte[2048];
            int len;
            StringBuilder sb = new StringBuilder();
            // InputStream.read是阻塞式，需要客户端发送结束命令
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            System.out.println(sb.toString());

            // 给客户端返回信息
            os = accept.getOutputStream();
            os.write("这是返回的信息".getBytes());
            os.flush();
            accept.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != accept) {
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
