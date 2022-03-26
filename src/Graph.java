import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
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
    double distance = iataToAeroport.get(iataSource).getCout();;
    ArrayList<String> itineraire = new ArrayList<String>();
    while(!baladeur.equals(iataSource)){
      itineraire.add(baladeur);
      System.out.println(baladeur);
      //System.out.println(iataToAeroport.get(baladeur).getNom());
      //System.out.println(itineraire);
      baladeur = path.get(baladeur);
    }
    itineraire.add(iataSource);
    for (int i=0; i<itineraire.size()-1; i++){
      distance += Util.distance(iataToAeroport.get(itineraire.get(i)).getLatitude(), iataToAeroport.get(itineraire.get(i)).getLongitude(),
              iataToAeroport.get(itineraire.get(i+1)).getLatitude(), iataToAeroport.get(itineraire.get(i+1)).getLongitude());
    }
    System.out.println(distance);
    return "";
  }

  public String calculerItineraireMiniminantDistance(String iataSource, String iataDestination){
    Set<Aeroport> etiquetteProvisoire= new TreeSet<Aeroport>(new Comparator<Aeroport>() {
      @Override
      public int compare(Aeroport o1, Aeroport o2) {
        //return o1.getCout() >= o2.getCout()?1:-1;
        int cout = Double.compare(o1.getCout(), o2.getCout());
        if (cout == 0) {
          return o1.getIata().compareTo(o2.getIata());
        }
        return cout;
      }
    });
    Set<Aeroport> etiquetteDef = new HashSet<Aeroport>();
    Map<String, String> path = new HashMap<String, String>();
    Aeroport aeroport = iataToAeroport.get(iataSource);

    while(!path.containsKey(iataDestination)){
      for(Vol vol: outputFlights.get(aeroport)) {
        if(!etiquetteDef.contains(iataToAeroport.get(vol.getDestination()))) {
          double tmpCout = aeroport.getCout() + Util.distance(iataToAeroport.get(vol.getSource()).getLatitude(), iataToAeroport.get(vol.getSource()).getLongitude(),
                  iataToAeroport.get(vol.getDestination()).getLatitude(), iataToAeroport.get(vol.getDestination()).getLongitude());
          if (etiquetteProvisoire.contains(iataToAeroport.get(vol.getDestination()))) {

            if(tmpCout < iataToAeroport.get(vol.getDestination()).getCout()){
              etiquetteProvisoire.remove(iataToAeroport.get(vol.getDestination()));

              iataToAeroport.get(vol.getDestination()).setCout(tmpCout);
              path.put(vol.getDestination(), aeroport.getIata());
              etiquetteProvisoire.add(iataToAeroport.get(vol.getDestination()));
            }
          }else{
            iataToAeroport.get(vol.getDestination()).setCout(tmpCout);
            path.put(vol.getDestination(), aeroport.getIata());
            etiquetteProvisoire.add(iataToAeroport.get(vol.getDestination()));
          }

        }
      }

      if(etiquetteProvisoire.isEmpty()) return "Aucun de vol vers cette aeroport";
      etiquetteDef.add(aeroport);
      aeroport = etiquetteProvisoire.iterator().next();
      etiquetteProvisoire.remove(aeroport);
    }

    String baladeur = iataDestination;
    double distance = iataToAeroport.get(iataSource).getCout();
    ArrayList<String> itineraire = new ArrayList<String>();
    while(!baladeur.equals(iataSource)){
      itineraire.add(baladeur);
      System.out.println(baladeur);
      baladeur = path.get(baladeur);
    }
    System.out.println(iataToAeroport.get(iataDestination).getCout());
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

