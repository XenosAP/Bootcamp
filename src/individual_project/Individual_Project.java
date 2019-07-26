/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package individual_project;

import Menus.HeadmasterMenu;
import Menus.StudentMenu;
import Menus.TrainerMenu;
import dao.UserDaoImplement;
import model.User;

/**
 *
 * @author creoo
 */
public class Individual_Project {//

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserDaoImplement dao=new UserDaoImplement();
        User user=dao.authentication();
        switch (user.getRoleid()) {
            case 1:
                HeadmasterMenu.HeadmasterMenu(user);
                break;
            case 2:
               TrainerMenu.TrainerMenu(user);
                break;
            case 3:
                StudentMenu.StudentMenu(user);
                break;
            default:
                break;
        }
    }

}
