
import control.Client;
import view.Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anh Nguyen
 */
public class Main {

    public static void main(String[] args) {
        Login loginView = new Login();
        new Client("localhost", 8888, loginView);
    }
}
