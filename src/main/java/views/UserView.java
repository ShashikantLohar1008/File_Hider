package views;

import dao.DataDAO;
import model.Data;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;
    public UserView(String email) {
        this.email=email;
    }

    public void home() throws Exception {
        do{
            System.out.println("Welcome "+this.email);
            System.out.println("press 1 to show hidden files");
            System.out.println("press 2 to hide new file");
            System.out.println("press 3 to unhide a file");
            System.out.println("press 0 to exit");
            Scanner sc=new Scanner(System.in);

            int ch=Integer.parseInt(sc.nextLine());

            switch(ch){
                case 1 ->{
                    List<Data> files= DataDAO.getAllFiles(this.email);
                    System.out.println("ID - File Name");
                    for (Data file:files){
                        System.out.println(file.getId()+" - "+file.getFileName());
                    }
                }
                case 2 ->{
                    System.out.println("Enter the file path");
                    String path=sc.nextLine();
                    File f=new File(path);
                    Data file=new Data(0,f.getName(),path,this.email);
                    DataDAO.hideFile(file);
                }
                case 3->{
                    List<Data> files= DataDAO.getAllFiles(this.email);
                    System.out.println("ID - File Name");
                    for (Data file:files){
                        System.out.println(file.getId()+" - "+file.getFileName());
                    }
                    System.out.println("enter the id of file to unhide");
                    int id=Integer.parseInt(sc.nextLine());
                    boolean isValidID=false;
                    for(Data file:files){
                        if(file.getId()==id){
                            isValidID=true;
                            break;
                        }
                    }
                    if(isValidID){
                        DataDAO.unhide(id);
                    }else{
                        System.out.println("wrong id");
                    }
                }
                case 0->{
                    System.exit(0);
                }
            }

        }while(true);
    }
}
