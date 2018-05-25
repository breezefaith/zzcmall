package cn.breezefaith.admin.entity;

public class Admin {
    final private String username="root";
    final private String password="root";
    private static Admin admin;
    public boolean isOK(String user,String passwd){
        return username.equals(user)&&password.equals(passwd);
    }
    public static Admin getInstance(){
        if(admin==null){
            admin=new Admin();
        }
        return admin;
    }
    private Admin(){

    }

}
