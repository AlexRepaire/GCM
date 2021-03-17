package gcm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

	import gcm.model.Patient;
import jdk.nashorn.internal.ir.ReturnNode;

	public class PatientDaoJDBC implements IPatientDao
	{
		Statement stmt;
		
		public PatientDaoJDBC() {
			try {
				 stmt = JdbcConnexion.connecter().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void add(Patient p) {
			// TODO Auto-generated method stub
			
	        try {
	        	
	        	String sql="insert into patient(nss,nom,prenom,adresse,DATE_NAISSANCE,ville) values("+p.getNss()+",'"+p.getNom()+"','"+p.getPrenom()+"','"+p.getAdresse()+"',"+p.getDateNaissance()+",'"+p.getVille()+"')";
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		}

		@Override
		public void delete(int nss) {
            try 
            {
                String sql="delete from patient where nss = "+nss+" ";
                stmt.execute(sql);
            } 
            catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			
		}

		@Override
		public void update(int nss, String ville, String adresse) {
            // TODO Auto-generated method stub
            try 
            {
                String sql="update patient set ville = '"+ville+"',adresse='"+adresse+"' where nss = "+nss+" ";
                stmt.execute(sql);
            } 
            catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

		@Override
		public List<Patient> findAll() {
			// TODO Auto-generated method stub
			List<Patient> list = new ArrayList<Patient>();
			try {
				ResultSet rs = stmt.executeQuery("select * from patient");
				while(rs.next()) {
					
					int nss = rs.getInt("nss");
					String nom = rs.getString("nom");
					String prenom = rs.getString("prenom");
					String adr = rs.getString("adresse");
					String ville = rs.getString("ville");
					//Date dateNaissance = rs.getDate("date_naissance");
					//remplir l'onjet patient
					Patient patient = new Patient();
					patient.setNss(nss);
					patient.setNom(nom);
					patient.setPrenom(prenom);
					patient.setAdresse(adr);
					//patient.setDateNaissance(dateNaissance);
					patient.setVille(ville);
					
					list.add(patient);
					
					
				}
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
			return list;
		}
		
		public boolean findUserByUsernameAndPassword(String username, String password) {
			try {
				ResultSet rs = stmt.executeQuery("select * from gcm_users where username='"+username+"' and password='"+password+"'");
				if (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
		public static void main(String[] args) {
	        PatientDaoJDBC patientDaoJDBC=new PatientDaoJDBC();
	        Patient p=new Patient();
	        p.setNss(15);
	        p.setPrenom("Xx");
	        p.setNom("YY");
	        p.setAdresse("ZZ");
	        //p.setDateNaissance(new Date());
	        p.setVille("Paris");
	        //patientDaoJDBC.add(p);
	        boolean resultat = patientDaoJDBC.findUserByUsernameAndPassword("test", "test");
	        System.out.println("resultat:"+resultat);
	    }
	}
