import java.io.*;

public class Graph {

  public Graph(File aeroport, File vols) throws IOException {

    parsing(aeroport);


  }

  public String calculerItineraireMinimisantNombreVol(String iataSource, String iataDestination){
    return "";
  }

  public String calculerItineraireMiniminantDistance(String iataSource, String iataDestination){
    return "";
  }

  private void parsing(File file)throws IOException{
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String ligne;
    while((ligne = buffer.readLine()) != null){
      //parse string and create aeroport/vols
      String[] ligneCoupe = ligne.split(",");
      if(ligneCoupe.length > 3){
        Aeroport aeroport = new Aeroport(ligneCoupe[0], ligneCoupe[1], ligneCoupe[2], ligneCoupe[3], Double.parseDouble(ligneCoupe[4]), Double.parseDouble(ligneCoupe[5]));
        System.out.println(ligneCoupe[0]);
        System.out.println(ligneCoupe[1]);
        System.out.println(ligneCoupe[2]);
        System.out.println(ligneCoupe[3]);
        System.out.println(ligneCoupe[4]);
        System.out.println(ligneCoupe[5]);
      }else{
        Vols vols = new Vols(ligneCoupe[0],ligneCoupe[1], ligneCoupe[2]);
      }
    }
  }
}
