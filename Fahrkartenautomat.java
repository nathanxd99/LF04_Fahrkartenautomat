import java.util.Scanner;

class Fahrkartenautomat {
  public static void main(String[] args) {
    boolean check = true;
    int count = 0;
    while (true) {
      Scanner tastatur = new Scanner(System.in);
      
      double zuZahlenderBetrag = fahrkartenbestellungErfassen(tastatur);
      
      double eingezahlterGesamtbetrag = fahrkartenBezahlen(zuZahlenderBetrag, tastatur);
      
      fahrkartenAusgeben();
      
      System.out.println("\n\n");
      
      rueckgeldAusgeben(eingezahlterGesamtbetrag - zuZahlenderBetrag);
      
      System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
      + "Wir wuenschen Ihnen eine gute Fahrt.");
      count ++;
      if (count > 100000) {
        check = false;
        }
      warte(10000);
      
      System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
  }

  public static double fahrkartenbestellungErfassen(Scanner tastatur) {
    double zuZahlenderBetrag = 0;
    int eingabe = 0;
    double ticketpreis = 0;
    String[] fahrkartenName = {"Einzelfahrschein Berlin AB [2,90 EUR]",
                                "Einzelfahrschein Berlin BC [3,30 EUR]",
                                "Einzelfahrschein Berlin ABC [3,60 EUR]",
                                "Kurzstrecke [1,90 EUR]",
                                "Tageskarte Berlin AB [8,60 EUR]",
                                "Tageskarte Berlin BC [9,00 EUR]",
                                "Tageskarte Berlin ABC [9,60 EUR]",
                                "Kleingruppen-Tageskarte Berlin AB [23,50 EUR]",
                                "Kleingruppen-Tageskarte Berlin BC [24,30 EUR]",
                                "Kleingruppen-Tageskarte Berlin ABC [24,90 EUR]",
                                "Bestellung abschlie�en"};
    double[] fahrkartenpreis = {2.9,3.3,3.6,1.9,8.6,9.0,9.6,23.5,24.3,24.9,20.0,25.5};  
    
    while (eingabe != fahrkartenName.length-1) {
      System.out.println("W�hlen Sie einen der folgenden Ticketm�glichkeiten aus:");
      for (int i = 0; i < fahrkartenName.length; i++){
          System.out.println(fahrkartenName[i] + " " + i);
        }
      
      eingabe = tastatur.nextInt();
      
      while (eingabe < 0 || eingabe > fahrkartenName.length-1) {
        System.out.println("Ung�ltige Eingabe");
        System.out.println("W�hlen Sie eine der oben stehenden M�glichkeiten aus!!!");
        eingabe = tastatur.nextInt();
      }
      
      if (eingabe != (fahrkartenName.length-1)) {
        System.out.println("Geben Sie an wie viele Tickets Sie ben�tigen (1-10):");
        int ticketanzahl = tastatur.nextInt();
        while(ticketanzahl < 1 || ticketanzahl > fahrkartenName.length+1) {
          System.out.println("Ung�ltige Eingabe!!");
          System.out.println("W�hlen Sie zwischen 1-10 Tickets!!");
          ticketanzahl = tastatur.nextInt();
        }
        zuZahlenderBetrag += fahrkartenpreis[eingabe] * ticketanzahl;
      }
      System.out.format("Zwischensumme: %4.2f Euro%n", zuZahlenderBetrag);
    }
    
    return zuZahlenderBetrag;
  }

  public static double fahrkartenBezahlen(double zuZahlen, Scanner tastatur) {
    // Geldeinwurf
    // -----------
    double eingezahlterGesamtbetrag = 0.0;
    double eingeworfeneMuenze;
    while (eingezahlterGesamtbetrag < zuZahlen) {
      System.out.format("Noch zu zahlen: %4.2f Euro%n", (zuZahlen - eingezahlterGesamtbetrag));
      System.out.print("Eingabe (mind. 5Ct, hoechstens 2 Euro): ");
      eingeworfeneMuenze = tastatur.nextDouble();
      eingezahlterGesamtbetrag += eingeworfeneMuenze;
    }
    
    return eingezahlterGesamtbetrag;
  }

  public static void fahrkartenAusgeben() {
    // Fahrscheinausgabe
    // -----------------
    System.out.println("\nFahrschein wird ausgegeben");
    for (int i = 0; i < 8; i++) {
      System.out.print("=");
      warte(250);
    }
    
  }

  public static void rueckgeldAusgeben(double rueckgabebetrag) {
    // Rueckgeldberechnung und -Ausgabe
    // -------------------------------
    if (rueckgabebetrag > 0.0) {
      System.out.format("Der Rueckgabebetrag in Hoehe von %4.2f Euro%n", rueckgabebetrag);
      System.out.println("wird in folgenden Muenzen ausgezahlt:");
      
      while (rueckgabebetrag >= 2.0) // 2 EURO-Münzen
      {
        System.out.println("2 EURO");
        rueckgabebetrag -= 2.0;
      }
      while (rueckgabebetrag >= 1.0) // 1 EURO-Münzen
      {
        System.out.println("1 EURO");
        rueckgabebetrag -= 1.0;
      }
      while (rueckgabebetrag >= 0.5) // 50 CENT-Münzen
      {
        System.out.println("50 CENT");
        rueckgabebetrag -= 0.5;
      }
      while (rueckgabebetrag >= 0.2) // 20 CENT-Münzen
      {
        System.out.println("20 CENT");
        rueckgabebetrag -= 0.2;
      }
      while (rueckgabebetrag >= 0.1) // 10 CENT-Münzen
      {
        System.out.println("10 CENT");
        rueckgabebetrag -= 0.1;
      }
      while (rueckgabebetrag >= 0.05)// 5 CENT-Münzen
      {
        System.out.println("5 CENT");
        rueckgabebetrag -= 0.05;
      }
    }
    
  }

  public static void warte(int millisekunden) {
    try {
      Thread.sleep(millisekunden);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
