import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public DB(){
		try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapde?useSSL=false", "root", "1234");
            stmt = con.createStatement();
        } catch (Exception e){
            System.out.println(e);
        }
	}
	
	public boolean insertAcc(String u, String p, String d){
		try{
			stmt.executeUpdate("INSERT INTO accounts (`username`, `password`, `description`) VALUES ('"+u+"', '"+p+"', '"+d+"');");
		} catch (Exception e){
			System.out.println (e);
			return false;
		}
		return true;
	}
	
	public boolean insertAllowList(String u){
		int id=0;
		rs = null;
		try{
			rs = stmt.executeQuery("select photoid from photos order by photoid desc;");
			rs.afterLast();
			while (rs.previous()) {
				  id = rs.getInt(1);
			}
		} catch (Exception e){
			System.out.println (e);
		}
		id=id+1;
		try{
			stmt.executeUpdate("INSERT INTO allowlist (`photoid`, `username`) VALUES ('"+id+"', '"+u+"');");
		} catch (Exception ee){
			System.out.println (ee);
			return false;
		}
		return true;
	}
	
	public boolean login(String u, String p){
		rs = null;
		
		try{
			rs = stmt.executeQuery("select username, password from accounts where username='"+u+"' and password='"+p+"'");
			if(rs.next())
				return true;
        } catch(Exception e){
            System.out.println(e);
        }
		return false;
	}
	
	public boolean insertTags(String tag){
		int id=0;
		rs = null;
		try{
			rs = stmt.executeQuery("select photoid from photos order by photoid desc;");
			rs.afterLast();
			while (rs.previous()) {
				  id = rs.getInt(1);
			}
		} catch (Exception e){
			System.out.println (e);
		}
		id=id+1;
		try{
			stmt.executeUpdate("INSERT INTO tags (`photoid`, `tagname`) VALUES ('"+id+"', '"+tag+"');");
		} catch (Exception ee){
			System.out.println (ee);
			return false;
		}
		return true;
	}
	
	public boolean insertTags1(String id, String tag){
		rs=null;
		try{
			stmt.executeUpdate("INSERT INTO tags (`photoid`, `tagname`) VALUES ('"+id+"', '"+tag+"');");
		} catch (Exception ee){
			System.out.println (ee);
			return false;
		}
		return true;
	}
	
	public ResultSet getUsers(String sql){
		rs = null;
		try{
			rs = stmt.executeQuery(sql);
        } catch(Exception e){
            System.out.println(e);
        }
        return rs;
	}
	
	public ResultSet getPhotos(String sql){
		rs = null;
		try{
			rs = stmt.executeQuery(sql);
        } catch(Exception e){
            System.out.println(e);
        }
        return rs;
	}
	
	public ResultSet getTags(String sql){
		rs = null;
		try{
			rs = stmt.executeQuery(sql);
        } catch(Exception e){
            System.out.println(e);
        }
        return rs;
	}
	
	public boolean insertPhoto(String u, String t, String d, String v, InputStream p){
		try{
			stmt.executeUpdate("INSERT INTO photos (`username`, `title`, `description`, `privacy`, `photo`) VALUES ('"+u+"', '"+t+"', '"+d+"', '"+v+"', '"+p+"');");
		} catch (Exception e){
			System.out.println (e);
			return false;
		}
		return true;
	}
}
