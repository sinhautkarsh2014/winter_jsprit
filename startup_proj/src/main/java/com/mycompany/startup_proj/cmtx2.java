/*
 * Licensed to GraphHopper GmbH under one or more contributor
 * license agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * GraphHopper GmbH licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 *@author utkarsh sinha
 */
package com.mycompany.startup_proj;

import au.com.bytecode.opencsv.CSVReader;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.cost.AbstractForwardVehicleRoutingTransportCosts;
import com.graphhopper.jsprit.core.problem.driver.Driver;
import com.graphhopper.jsprit.core.problem.vehicle.Vehicle;
import com.graphhopper.jsprit.core.util.Coordinate;
import com.graphhopper.jsprit.core.problem.cost.TransportDistance;
import com.graphhopper.jsprit.core.util.Locations;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.lang.Math;
 
/**
 * @author stefan schroeder
 */
public class cmtx2 extends AbstractForwardVehicleRoutingTransportCosts implements TransportDistance  {

   private Locations locations;

    public cmtx2(Locations locations) throws FileNotFoundException, IOException {
        super();
        this.r1 = new FileReader("cdtmatrix.csv");
        this.locations = locations;
        this.reader=new CSVReader(r1);
        this.myEntries = reader.readAll();
        
    }
    
    @Override
    public String toString() {
        return "[name=cmtx2]";
    }
    FileReader r1;
    CSVReader reader;
    List<String[]> myEntries;
    
     
    
    @Override
    public double getTransportCost(Location from, Location to, double time, Driver driver, Vehicle vehicle) {
        String f=from.getId();
        String t=to.getId();
        double dist=0;
        int di=1;
            while(true){if(f.equals(myEntries.get(di)[0])&&t.equals(myEntries.get(di)[1])||f.equals(myEntries.get(di)[1])&&t.equals(myEntries.get(di)[0]))
                                                  {dist=Integer.parseInt(myEntries.get(di)[9]); break;}
            di++;
            if(di==903){break;}}
        double distleg1=0;
        int d1k=1;
        while(true){if("0".equals(myEntries.get(d1k)[0])&&f.equals(myEntries.get(d1k)[1]))
                        {distleg1=Integer.parseInt(myEntries.get(d1k)[9]); break;}
                         d1k++;
                         if(d1k==41){break;}
                        }
        double distleg2=0;
        int d2k=1;
        while(true){if("0".equals(myEntries.get(d2k)[0])&&t.equals(myEntries.get(d2k)[1]))
                        {distleg2=Integer.parseInt(myEntries.get(d2k)[9]); break;}
                         d2k++;
                         if(d2k==41){break;}
                        }
        if(f.equals(t)){double costs=0; return costs;} 
        if (vehicle==null){double costs=99999.9; return costs;}
        String v=vehicle.getId();
        double costs=0;
        if (v.equals("vehicle1")){
            int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                          {if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[11].equals("MT")||myEntries.get(i)[12].equals("MT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("GT")&&!myEntries.get(i)[14].equals("GT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("Upcountry")&&!myEntries.get(i)[14].equals("Upcountry"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("ACD")&&!myEntries.get(i)[14].equals("ACD"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&dist+distleg1<=(1+0.3)*distleg2)
                                    {int j=1;
                                    double costs_to=0;
                                     while(true){if("0".equals(myEntries.get(j)[0])&&t.equals(myEntries.get(j)[1]))
                                     {costs_to=Integer.parseInt(myEntries.get(j)[2]); break;}
                                                 j++;
                                                 if(j==41){break;}
                                                  }       
                                     int k=1;
                                     double costs_from=0;
                                     while(true){if("0".equals(myEntries.get(k)[0])&&f.equals(myEntries.get(k)[1]))
                                     {costs_from=Integer.parseInt(myEntries.get(k)[2]); break;}
                                                 k++;
                                                 if(k==41){break;}
                                                  } 
                                      costs=java.lang.Math.max(0,costs_to-costs_from)+300; return costs;                   }
                          else if(!((!f.equals("0"))||(!t.equals("0")))){costs=99999; return costs;}
                          else {costs=Integer.parseInt(myEntries.get(i)[2]); break;}
                          }
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle2")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                          {if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[11].equals("MT")||myEntries.get(i)[12].equals("MT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("GT")&&!myEntries.get(i)[14].equals("GT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("Upcountry")&&!myEntries.get(i)[14].equals("Upcountry"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("ACD")&&!myEntries.get(i)[14].equals("ACD"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&dist+distleg1<=(1+0.3)*distleg2)
                                    {int j=1;
                                    double costs_to=0;
                                     while(true){if("0".equals(myEntries.get(j)[0])&&t.equals(myEntries.get(j)[1]))
                                     {costs_to=Integer.parseInt(myEntries.get(j)[3]); break;}
                                                 j++;
                                                 if(j==41){break;}
                                                  }       
                                     int k=1;
                                     double costs_from=0;
                                     while(true){if("0".equals(myEntries.get(k)[0])&&f.equals(myEntries.get(k)[1]))
                                     {costs_from=Integer.parseInt(myEntries.get(k)[3]); break;}
                                                 k++;
                                                 if(k==41){break;}
                                                  } 
                                      costs=java.lang.Math.max(0,costs_to-costs_from)+300; return costs;                                          }
                          else if(!((!f.equals("0"))||(!t.equals("0")))){costs=99999; return costs;}
                          else {costs=Integer.parseInt(myEntries.get(i)[3]); break;}
                          }
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle3")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                          {if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[11].equals("MT")||myEntries.get(i)[12].equals("MT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("GT")&&!myEntries.get(i)[14].equals("GT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("Upcountry")&&!myEntries.get(i)[14].equals("Upcountry"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("ACD")&&!myEntries.get(i)[14].equals("ACD"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&dist+distleg1<=(1+0.3)*distleg2)
                                    {int j=1;
                                    double costs_to=0;
                                     while(true){if("0".equals(myEntries.get(j)[0])&&t.equals(myEntries.get(j)[1]))
                                     {costs_to=Integer.parseInt(myEntries.get(j)[4]); break;}
                                                 j++;
                                                 if(j==41){break;}
                                                  }       
                                     int k=1;
                                     double costs_from=0;
                                     while(true){if("0".equals(myEntries.get(k)[0])&&f.equals(myEntries.get(k)[1]))
                                     {costs_from=Integer.parseInt(myEntries.get(k)[4]); break;}
                                                 k++;
                                                 if(k==41){break;}
                                                  } 
                                      costs=java.lang.Math.max(0,costs_to-costs_from)+300; return costs;                                        
                                                                               }
                          else if(!((!f.equals("0"))||(!t.equals("0")))){costs=99999; return costs;}
                          else {costs=Integer.parseInt(myEntries.get(i)[4]); break;}
                          }
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle4")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                          {if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[11].equals("MT")||myEntries.get(i)[12].equals("MT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("GT")&&!myEntries.get(i)[14].equals("GT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("Upcountry")&&!myEntries.get(i)[14].equals("Upcountry"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("ACD")&&!myEntries.get(i)[14].equals("ACD"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&dist+distleg1<=(1+0.3)*distleg2)
                                    {int j=1;
                                    double costs_to=0;
                                     while(true){if("0".equals(myEntries.get(j)[0])&&t.equals(myEntries.get(j)[1]))
                                     {costs_to=Integer.parseInt(myEntries.get(j)[5]); break;}
                                                 j++;
                                                 if(j==41){break;}
                                                  }       
                                     int k=1;
                                     double costs_from=0;
                                     while(true){if("0".equals(myEntries.get(k)[0])&&f.equals(myEntries.get(k)[1]))
                                     {costs_from=Integer.parseInt(myEntries.get(k)[5]); break;}
                                                 k++;
                                                 if(k==41){break;}
                                                  } 
                                      costs=java.lang.Math.max(0,costs_to-costs_from)+300; return costs;                                         
                                                                               }
                          else if(!((!f.equals("0"))||(!t.equals("0")))){costs=99999; return costs;}
                          else {costs=Integer.parseInt(myEntries.get(i)[5]); break;}
                          }
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle5")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                          {if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[11].equals("MT")||myEntries.get(i)[12].equals("MT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("GT")&&!myEntries.get(i)[14].equals("GT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("Upcountry")&&!myEntries.get(i)[14].equals("Upcountry"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("ACD")&&!myEntries.get(i)[14].equals("ACD"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&dist+distleg1<=(1+0.3)*distleg2)
                                    {int j=1;
                                    double costs_to=0;
                                     while(true){if("0".equals(myEntries.get(j)[0])&&t.equals(myEntries.get(j)[1]))
                                     {costs_to=Integer.parseInt(myEntries.get(j)[6]); break;}
                                                 j++;
                                                 if(j==41){break;}
                                                  }       
                                     int k=1;
                                     double costs_from=0;
                                     while(true){if("0".equals(myEntries.get(k)[0])&&f.equals(myEntries.get(k)[1]))
                                     {costs_from=Integer.parseInt(myEntries.get(k)[6]); break;}
                                                 k++;
                                                 if(k==41){break;}
                                                  } 
                                      costs=java.lang.Math.max(0,costs_to-costs_from)+300; return costs;                                       
                                                                               }
                          else if(!((!f.equals("0"))||(!t.equals("0")))){costs=99999; return costs;}
                          else {costs=Integer.parseInt(myEntries.get(i)[6]); break;}
                          }
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle6")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                          {if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[11].equals("MT")||myEntries.get(i)[12].equals("MT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("GT")&&!myEntries.get(i)[14].equals("GT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("Upcountry")&&!myEntries.get(i)[14].equals("Upcountry"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("ACD")&&!myEntries.get(i)[14].equals("ACD"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&dist+distleg1<=(1+0.3)*distleg2)
                                    {int j=1;
                                    double costs_to=0;
                                     while(true){if("0".equals(myEntries.get(j)[0])&&t.equals(myEntries.get(j)[1]))
                                     {costs_to=Integer.parseInt(myEntries.get(j)[7]); break;}
                                                 j++;
                                                 if(j==41){break;}
                                                  }       
                                     int k=1;
                                     double costs_from=0;
                                     while(true){if("0".equals(myEntries.get(k)[0])&&f.equals(myEntries.get(k)[1]))
                                     {costs_from=Integer.parseInt(myEntries.get(k)[7]); break;}
                                                 k++;
                                                 if(k==41){break;}
                                                  } 
                                      costs=java.lang.Math.max(0,costs_to-costs_from)+300; return costs;                                            
                                                                               }
                          else if(!((!f.equals("0"))||(!t.equals("0")))){costs=99999; return costs;}
                          else {costs=Integer.parseInt(myEntries.get(i)[7]); break;}
                          }
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle7")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                          {if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[11].equals("MT")||myEntries.get(i)[12].equals("MT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("GT")&&!myEntries.get(i)[14].equals("GT"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("Upcountry")&&!myEntries.get(i)[14].equals("Upcountry"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&(myEntries.get(i)[13].equals("ACD")&&!myEntries.get(i)[14].equals("ACD"))){costs=99999; return costs;}
                          else if((!f.equals("0"))&&(!f.equals(t))&&dist+distleg1<=(1+0.3)*distleg2)
                                    {int j=1;
                                    double costs_to=0;
                                     while(true){if("0".equals(myEntries.get(j)[0])&&t.equals(myEntries.get(j)[1]))
                                     {costs_to=Integer.parseInt(myEntries.get(j)[8]); break;}
                                                 j++;
                                                 if(j==41){break;}
                                                  }       
                                     int k=1;
                                     double costs_from=0;
                                     while(true){if("0".equals(myEntries.get(k)[0])&&f.equals(myEntries.get(k)[1]))
                                     {costs_from=Integer.parseInt(myEntries.get(k)[8]); break;}
                                                 k++;
                                                 if(k==41){break;}
                                                  } 
                                      costs=java.lang.Math.max(0,costs_to-costs_from)+300; return costs;                                          
                                                                               }
                          else if(!((!f.equals("0"))||(!t.equals("0")))){costs=99999; return costs;}
                          else {costs=Integer.parseInt(myEntries.get(i)[8]); break;}
                          }
            i++;
            if(i==903){break;}}}

        
        return costs;
    }

    

    

    @Override
    public double getTransportTime(Location from, Location to, double time, Driver driver, Vehicle vehicle) {
        String f=from.getId();
        String t=to.getId();
        if(f.equals(t)){double tt=0; return tt;} 
        double tt=0;
        if (vehicle==null){tt=99999.9; return tt;}
        String v=vehicle.getId();
        /*Time Matrix to be read here*/
        int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                                                  {tt=Integer.parseInt(myEntries.get(i)[10]); break;}
            i++;
            if(i==903){break;}}
        return tt;
    }
    
        @Override
    public double getDistance(Location from, Location to, double departureTime, Vehicle vehicle) {

        String f=from.getId();
        String t=to.getId();
        if(f.equals(t)){double d=0; return d;} 
        if (vehicle==null){double d=99999; return d;}
        String v=vehicle.getId();
        /*Distance matrix to be read here*/
        double d=0;
        int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                                                  {d=Integer.parseInt(myEntries.get(i)[9]); break;}
            i++;
            if(i==903){break;}}
        return d;}
    
}