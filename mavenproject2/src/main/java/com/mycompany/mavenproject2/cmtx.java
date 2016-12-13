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
 *
 */
package com.mycompany.mavenproject2;

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
 
/**
 * @author stefan schroeder
 */
public class cmtx extends AbstractForwardVehicleRoutingTransportCosts implements TransportDistance  {

   private Locations locations;

    public cmtx(Locations locations) throws FileNotFoundException, IOException {
        super();
        this.r1 = new FileReader("cdtmatrix.csv");
        this.locations = locations;
        this.reader=new CSVReader(r1);
        this.myEntries = reader.readAll();
    }
    
    @Override
    public String toString() {
        return "[name=cmtx]";
    }
    FileReader r1;
    CSVReader reader;
    List<String[]> myEntries;
     
    
    @Override
    public double getTransportCost(Location from, Location to, double time, Driver driver, Vehicle vehicle) {
        String f=from.getId();
        String t=to.getId();
        if(f.equals(t)){double costs=0; return costs;} 
        if (vehicle==null){double costs=99999.9; return costs;}
        String v=vehicle.getId();
        double costs=0;
        if (v.equals("vehicle1")){
            int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                                                  {costs=Integer.parseInt(myEntries.get(i)[2]); break;}
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle2")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                                                  {costs=Integer.parseInt(myEntries.get(i)[3]); break;}
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle3")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                                                  {costs=Integer.parseInt(myEntries.get(i)[4]); break;}
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle4")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                                                  {costs=Integer.parseInt(myEntries.get(i)[5]); break;}
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle5")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                                                  {costs=Integer.parseInt(myEntries.get(i)[6]); break;}
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle6")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                                                  {costs=Integer.parseInt(myEntries.get(i)[7]); break;}
            i++;
            if(i==903){break;}}}
        else if (v.equals("vehicle7")){int i=1;
            while(true){if(f.equals(myEntries.get(i)[0])&&t.equals(myEntries.get(i)[1])||f.equals(myEntries.get(i)[1])&&t.equals(myEntries.get(i)[0]))
                                                  {costs=Integer.parseInt(myEntries.get(i)[8]); break;}
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