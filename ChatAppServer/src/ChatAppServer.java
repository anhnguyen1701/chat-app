
import control.RoutesHandler;
import view.ServerView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anh Nguyen
 */
public class ChatAppServer {    

    public static void main(String[] args) {
        new RoutesHandler(new ServerView());
    }
}
