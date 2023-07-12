/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gatosapp;

import Services.GatosService;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Roberto Marroquín
 */
public class GatosApp {

    public static void main(String[] args) throws IOException {

        int opMenu = -1;  
        String[] btns = {"1. Ver Gatos","2. Salir"};
        
        do{
            //Menu principal
            String op  = (String) JOptionPane.showInputDialog(null,"Gatitos Java",
                    "MENU PRINCIPAL",JOptionPane.INFORMATION_MESSAGE,null,btns,btns[0]);
            
            //Validar opcion seleccionada
            for(int i = 0; i<btns.length;i++){
                if(op.equals(btns[i])){
                    opMenu= i;
                }
            }
            
            
            switch(opMenu){
                case 0:
                    GatosService.verGatitos();
                    break;
                default:
                    break;
                    
            }
        }while(opMenu!=1);
    }
}
