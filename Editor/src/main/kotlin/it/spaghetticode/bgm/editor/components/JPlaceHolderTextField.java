package it.spaghetticode.bgm.editor.components;

import javax.swing.*;
import java.awt.*;

public class JPlaceHolderTextField extends JTextField{
    public String placeHolder = "";

    public void setPlaceHolder(String s){
        placeHolder = s;
    }
    public String getPlaceHolder(){
        return placeHolder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(getFont());
        g.setColor(Color.GRAY);

        if(getText().equals("")){
            g.drawString(placeHolder, 5, getHeight()-(getHeight()/2-5));
        }else{
            return;
        }
    }
}
