import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.print.attribute.HashPrintJobAttributeSet;

public class Graph {

  private Map<Aeroport, Set<Vol>>outputFlights;
  //key: iata, value: aeroport
  private Map<String, Aeroport> iataToAeroport;

  public Graph(File aeroport, File vols) throws IOException {
    outputFlights = new HashMap<Aeroport,Set<Vol>>();
    iataToAeroport = new HashMap<String, Aeroport>();
    parsing(aeroport);
    parsing(vols);

  }

  public String calculerItineraireMinimisantNombreVol(String iataSource, String iataDestination){
    Aeroport aeroport = iataToAeroport.get(iataSource);
    Deque<Aeroport> filePath = new ArrayDeque<Aeroport>();
    Set<Aeroport> visited = new HashSet<Aeroport>();
    Map<String, String> path = new HashMap<String, String>();
    path.put("Start", iataSource);
    filePath.add(aeroport);
    while(!filePath.isEmpty() && !path.containsKey(iataDestination)){
      aeroport = filePath.removeFirst();
      for(Vol vol: outputFlights.get(aeroport)){
        if(!visited.contains(iataToAeroport.get(vol.getDestination()))) {
          filePath.add(iataToAeroport.get(vol.getDestination()));
          visited.add(iataToAeroport.get(vol.getDestination()));
          path.put(vol.getDestination(), aeroport.getIata());
        }
      }
    }

    if(!path.containsKey(iataDestination)){
      System.out.println("Il n'y a pas de vols possible");
    }

    String baladeur = iataDestination;
    ArrayList<String> itineraire = new ArrayList<String>();
    while(!baladeur.equals(iataSource)){
      itineraire.add(baladeur);
      System.out.println(baladeur);
      baladeur = path.get(baladeur);
    }

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
        outputFlights.put(aeroport, new HashSet<Vol>());
        iataToAeroport.put(aeroport.getIata(), aeroport);
      }else{
        Vol vol = new Vol(ligneCoupe[0],ligneCoupe[1], ligneCoupe[2]);
        outputFlights.get(iataToAeroport.get(vol.getSource())).add(vol);
      }
    }
  }
}

/*
System.out.println(ligneCoupe[0]);
        System.out.println(ligneCoupe[1]);
        System.out.println(ligneCoupe[2]);
        System.out.println(ligneCoupe[3]);
        System.out.println(ligneCoupe[4]);
        System.out.println(ligneCoupe[5]);
 */
