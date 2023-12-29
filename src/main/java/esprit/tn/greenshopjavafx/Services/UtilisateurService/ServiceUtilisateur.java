package esprit.tn.greenshopjavafx.Services.UtilisateurService;


import esprit.tn.greenshopjavafx.Entities.Utilisateur.*;
import esprit.tn.greenshopjavafx.Services.IService;
import esprit.tn.greenshopjavafx.Utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ServiceUtilisateur implements IService<Utilisateur> {

   private Connection con= DataSource.getInstance().getCon();

   private Statement ste;


   public ServiceUtilisateur()
   {
       try {
               ste = con.createStatement();
       }catch (SQLException e)
       {
           System.out.println(e);
       }
   }
    @Override
    public  void  ajouter(Utilisateur utilisateur) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        String req = "INSERT INTO `utilisateur` (`nom`, `email`, `password`, `Type`, `Date_inscription`) VALUES ('"
                + utilisateur.getNom() + "', '"
                + utilisateur.getEmail() + "', '"
                + utilisateur.getPassword() + "', '"
                + utilisateur.getUserType() + "', '"
                + currentDate + "');";
        int  res=   ste.executeUpdate(req);


    }

    @Override
    public void update(Utilisateur utilisateur) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        String req = "UPDATE utilisateur SET nom = '" + utilisateur.getNom()+
                "', email = '" + utilisateur.getEmail() +
                "', password = '" + utilisateur.getPassword() +
                "', Date_inscription = '" + currentDate+
                "' WHERE id = " + utilisateur.getId();

        int res = ste.executeUpdate(req);

    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE id = " + id;
        int res = ste.executeUpdate(req);
    }

        public int  login(String email, String motDePasse) throws SQLException {
            String req = "SELECT * FROM utilisateur WHERE email = '" + email + "' AND password = '" + motDePasse + "'";
            ResultSet resultSet = ste.executeQuery(req);

            return resultSet.next()?resultSet.getInt("id"):-1;
        }



    @Override
    public ArrayList<Utilisateur> readAll() throws SQLException {
       ArrayList<Utilisateur> list=new ArrayList<>();
        try {
            ResultSet resultSet = ste.executeQuery("select * from utilisateur");
            while (resultSet.next())
            {
                int id=resultSet.getInt("id");
                String nom=resultSet.getString(2);
                String email=resultSet.getString(3);
                String password=resultSet.getString(4);
                UserType ut=UserType.valueOf(resultSet.getString(5));
               Utilisateur u=new Utilisateur(id,nom,email,password, ut);
               list.add(u);
            }

        }catch (SQLException e)
        {
            System.out.println(e);
        }




       return list;
    }

    @Override
    public Utilisateur get(int id) throws SQLException {
        Utilisateur utilisateur = null;
        String req = "SELECT * FROM utilisateur WHERE id = " + id;
        ResultSet resultSet = ste.executeQuery(req);


        if (resultSet.next()) {
            int Id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            UserType ut=UserType.valueOf(resultSet.getString("Type"));

            utilisateur = new Utilisateur(Id, nom, email, password, ut);
        }

        return utilisateur;
    }


    public int count() throws SQLException {
        String sql = "SELECT COUNT(id) FROM utilisateur WHERE Type = 'CUSTOMER'";
        ResultSet resultSet = ste.executeQuery(sql);
        int count = 0;
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }

}

