/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static Utils.Class.EDIT_INFO_FORM;
import static Utils.Class.HOME;
import static Utils.Class.LOGIN_FORM;
import static Utils.Class.MAIN_FRAME;
import static Utils.Class.REGISTER_FORM;
import static Utils.Class.STATISTIC;
import static Utils.Constant.ADDRESS;
import static Utils.Constant.PORT;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKey;

public class Client {

	private Socket socket = null;
	public BufferedWriter out = null;
	public BufferedReader in = null;
	private Scanner scanner = null;
	private boolean flag = true;

	byte[] ServerpubKeyByte;
	SecretKey secKey;
	ObjectInputStream objectInput;
	File f;

	public Client() {

	}

	public Client(String address, int port) {
		try {
			socket = new Socket(address, port);
			System.out.println("Da ket noi");
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			scanner = new Scanner(System.in);
			objectInput = new ObjectInputStream(socket.getInputStream());
			String exit = "";
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
		Receive r = new Receive();
		r.start();
	}


	class Receive extends Thread {

		@Override
		public void run() {
			try {
				System.out.println("Listening");
				while (flag) {
                                        Vector<Vector<String>> result = new Vector<Vector<String>>();
					String line = in.readLine();
					System.out.println("Line :" + line);
					String[] array = line.split("\\]\\:\\$\\:\\[");
					String command = array[0];
					switch (command) {
					// Nếu login thành công
                                        case "rankloaded":
                                                //synchronized (objectInput) {
                                                    result = (Vector<Vector<String>>)objectInput.readObject();
                                                //}
                                                STATISTIC.loadRankingTable(result);
                                                break;
                                        case "percentloaded":
                                                //synchronized (objectInput) {
                                                    result = (Vector<Vector<String>>)objectInput.readObject();
                                                //}
                                                STATISTIC.loadWinPercentTable(result);
                                                break;
                                        case "searchloaded":
                                                //synchronized (objectInput) {
                                                    result = (Vector<Vector<String>>)objectInput.readObject();
                                                //}
                                                STATISTIC.loadCheckSTTTable(result);
                                                break;
                                        case "searcherror":
                                            //result = (Vector<Vector<String>>)objectInput.readObject();
                                            STATISTIC.searchFail();
                                            break;
					case "loginsuccess":
						LOGIN_FORM.LoginSuccess();
						break;
					// Nếu login thất bại
					case "loginfail":
						LOGIN_FORM.LoginFail();
						break;
					// Nếu đăng ký thất bại, email không đúng
					case "emailkhonghople":
						REGISTER_FORM.EmailKhongHopLe();
						break;
					// Nếu đăng ký thành công
					case "signupsuccess":
						REGISTER_FORM.EmailHopLe();
						break;
					// Nếu sql lỗi trong khi đăng ký
					case "signupfail":
						REGISTER_FORM.SignUpFail();
						break;
					// Nếu update info lỗi
					case "updateinfoerror":
						EDIT_INFO_FORM.EditInfoFail();
						break;
					// Nếu update info thành công
					case "updateinfosuccess":
						EDIT_INFO_FORM.EditInfoSuccess();
						break;
					// tải dữ liệu user
					case "UserInfo":
						EDIT_INFO_FORM.loadInfoInEdit(line);
						break;
					//
					case "wait":
						break;
					// Bắt đầu game
					case "gamestart":
						MAIN_FRAME.ready = true;
						break;
					// thời gian của ván game
					case "time":
						MAIN_FRAME.setTime(array[1]);
						break;
					// số may mắn
					case "lucky":
						MAIN_FRAME.setLuckyNumber(array[1]);
						break;
					// Nếu đối phương nhấn
					case "opponentClicked":
						MAIN_FRAME.setNumber(array[1], Color.RED);
						break;
					// Nếu mình nhấn
					case "IClicked":
						MAIN_FRAME.setNumber(array[1], Color.BLUE);
						break;
					// Điểm của người chơi 1
					case "point1":
						MAIN_FRAME.pointPanel.setPlayer1(array[1]);
						break;
					// Điểm của người chơi 2
					case "point2":
						MAIN_FRAME.pointPanel.setPlayer2(array[1]);
						break;
					// Số tiếp theo, không phải là số may mắn
					case "next":
						MAIN_FRAME.setNextNumber(array[1]);
						break;
					// Dùng tăng sức mạnh
					case "PowerUp":
						MAIN_FRAME.pointPanel.getPowerUp().setEnabled(true);
						break;
					// Bị đen màn hình trong 2 giây
					case "blackout":
						MAIN_FRAME.Blackout();
						break;
					// Kết quả trận đấu
					case "result":
						MAIN_FRAME.Result(array[1]);
						break;
					case "nothing":
						break;
					}
					if (line.equals("bye")) {
						flag = false;
						return;
					}

				}
			} catch (IOException ex) {
				exit();
				flag = false;
			} catch (ClassNotFoundException ex) {
                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
				exit();
			}
		}
	}

	public void send(String cmd, String s) {
		try {
			String kq = cmd + "]:$:[" + s;
			out.write((kq));
			out.newLine();
			out.flush();
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void sendOnlyCmd(String cmd) {
		try {
			out.write((cmd));
			out.newLine();
			out.flush();
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public String receive() throws IOException {
		return (in.readLine());
	}

	// ^
	// |
// Không trả về result nữa, client sẽ gọi hàm xử lý của class tương ứng với dữ liệu server trả về, xem hàm run()|, xem LoginForm
	public void verifyLogin(String email, char[] passwd) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(passwd);
			String loginInput = email + "]:$:[" + hashPasswordMD5(sb.toString());
			send("login", loginInput);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void signup(String email, char[] passwd, String name, String gender, String DoB) {
		StringBuilder sb = new StringBuilder();
		sb.append(passwd);
		String input;
		try {
			input = email + "]:$:[" + hashPasswordMD5(sb.toString()) + "]:$:[" + name + "]:$:[" + gender + "]:$:["
					+ DoB;
			send("signup", input);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
// Điều chỉnh lại giùm t , 

	public int editInfo(String name, String gender, String DoB, char[] passwd) {
		int result = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(passwd);
		String pass;
		try {
			pass = hashPasswordMD5(sb.toString());
			// Trường hợp người dùng ko nhập gì vào field password trong frame edit
			if (sb.toString().equals("")) {
				pass = "nochange";
			}
			String input = name + "]:$:[" + gender + "]:$:[" + DoB + "]:$:[" + pass;
			send("editInfo", input);
			String kq = receive();
			if (kq.equals("success")) {
				result = 1;
			}
		} catch (NoSuchAlgorithmException | IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public void getInfoOfUser() throws IOException {
		sendOnlyCmd("UserInfo");
	}
//
        public void getTable(){
            sendOnlyCmd("Statistic");
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                System.out.println("Thread  interrupted.");
            }
        }
        public void getPTable(){
            sendOnlyCmd("PercentRank");
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                System.out.println("Thread  interrupted.");
            }
        }
        public void getSearch(String key){
            send("SearchRank", key);
        }
        
//	public Vector<Vector<String>> getRankingTable() throws IOException, ClassNotFoundException {
//		//sendOnlyCmd("Statistic");
//		Vector<Vector<String>> result = new Vector<Vector<String>>();
//		result = (Vector<Vector<String>>)objectInput.readObject();
//		return result;
//	}
//
//	public Vector<Vector<String>> getPercentRankingTable() throws IOException, ClassNotFoundException {
//		//sendOnlyCmd("PercentRank");
//		Vector<Vector<String>> result = new Vector<Vector<String>>();
//		result = (Vector<Vector<String>>)objectInput.readObject();
//		return result;
//	}
//
//	public Vector<Vector<String>> getSearchRank() throws IOException, ClassNotFoundException {
//		//send("SearchRank", key);
//		Vector<Vector<String>> result = new Vector<Vector<String>>();
//		result = (Vector<Vector<String>>)objectInput.readObject();
//		return result;
//	}

	public String hashPasswordMD5(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		StringBuilder sb = new StringBuilder(32);
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		// Returns the result
		return sb.toString();
	}

	public void exit() {
		try {
			System.out.println("Connection Closing..");
			if (scanner != null) {
				scanner.close();
				System.out.println(" Scanner Closed");
			}
			if (in != null) {
				in.close();
				System.out.println(" Socket Input Stream Closed");
			}

			if (out != null) {
				out.close();
				System.out.println("Socket Out Closed");
			}
			if (socket != null) {
				socket.close();
				System.out.println("Socket Closed");
			}

		} catch (IOException ie) {
			System.out.println("Socket Close Error");
		}
	}

	public static void main(String[] args) throws IOException, UnknownHostException, NoSuchAlgorithmException {
		Client client2 = new Client(ADDRESS, PORT);
		HOME = new Home(client2);
		HOME.setVisible(true);

	}

}
