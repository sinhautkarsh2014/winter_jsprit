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

import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.cost.AbstractForwardVehicleRoutingTransportCosts;
import com.graphhopper.jsprit.core.problem.driver.Driver;
import com.graphhopper.jsprit.core.problem.vehicle.Vehicle;
import com.graphhopper.jsprit.core.util.Coordinate;
import com.graphhopper.jsprit.core.problem.cost.TransportDistance;

import com.graphhopper.jsprit.core.util.Locations;
 
/**
 * @author stefan schroeder
 */
public class cmtx extends AbstractForwardVehicleRoutingTransportCosts implements TransportDistance  {

   private Locations locations;

    public cmtx(Locations locations) {
        super();
        this.locations = locations;
    }
    
    @Override
    public String toString() {
        return "[name=cmtx]";
    }
    
    
    @Override
    public double getTransportCost(Location from, Location to, double time, Driver driver, Vehicle vehicle) {
        String f=from.getId();
        String t=to.getId();
        if (vehicle==null){double costs=99999.9; return costs;}
        String v=vehicle.getId();
        double costs=2.3;
        if (v.equals("vehicle1")){
            }
        else if (v.equals("vehicle2")){if(f.equals("0")&&t.equals("1")||f.equals("1")&&t.equals("0")){costs=68;}
            else if (f.equals("0")&&t.equals("2")||f.equals("2")&&t.equals("0")){costs=67;/*cost matrix for vehicle2 here*/}
            else if (f.equals("0")&&t.equals("3")||f.equals("3")&&t.equals("0")){costs=68;/*cost matrix for vehicle3 here*/}
            else if (f.equals("2")&&t.equals("3")||f.equals("3")&&t.equals("2")){costs=45;/*cost matrix for vehicle4 here*/}
            else if (f.equals("1")&&t.equals("3")||f.equals("3")&&t.equals("1")){costs=0;/*cost matrix for vehicle5 here*/}
            else if (f.equals("1")&&t.equals("2")||f.equals("2")&&t.equals("1")){costs=45;/*cost matrix for vehicle6 here*/}}
        else if (v.equals("vehicle3")){if(f.equals("0")&&t.equals("1")||f.equals("1")&&t.equals("0")){costs=68;}
            else if (f.equals("0")&&t.equals("2")||f.equals("2")&&t.equals("0")){costs=67;/*cost matrix for vehicle2 here*/}
            else if (f.equals("0")&&t.equals("3")||f.equals("3")&&t.equals("0")){costs=68;/*cost matrix for vehicle3 here*/}
            else if (f.equals("2")&&t.equals("3")||f.equals("3")&&t.equals("2")){costs=45;/*cost matrix for vehicle4 here*/}
            else if (f.equals("1")&&t.equals("3")||f.equals("3")&&t.equals("1")){costs=0;/*cost matrix for vehicle5 here*/}
            else if (f.equals("1")&&t.equals("2")||f.equals("2")&&t.equals("1")){costs=45;/*cost matrix for vehicle6 here*/}}
        else if (v.equals("vehicle4")){if(f.equals("0")&&t.equals("1")||f.equals("1")&&t.equals("0")){costs=68;}
            else if (f.equals("0")&&t.equals("2")||f.equals("2")&&t.equals("0")){costs=67;/*cost matrix for vehicle2 here*/}
            else if (f.equals("0")&&t.equals("3")||f.equals("3")&&t.equals("0")){costs=68;/*cost matrix for vehicle3 here*/}
            else if (f.equals("2")&&t.equals("3")||f.equals("3")&&t.equals("2")){costs=45;/*cost matrix for vehicle4 here*/}
            else if (f.equals("1")&&t.equals("3")||f.equals("3")&&t.equals("1")){costs=0;/*cost matrix for vehicle5 here*/}
            else if (f.equals("1")&&t.equals("2")||f.equals("2")&&t.equals("1")){costs=45;/*cost matrix for vehicle6 here*/}}
        else if (v.equals("vehicle5")){if(f.equals("0")&&t.equals("1")||f.equals("1")&&t.equals("0")){costs=68;}
            else if (f.equals("0")&&t.equals("2")||f.equals("2")&&t.equals("0")){costs=67;/*cost matrix for vehicle2 here*/}
            else if (f.equals("0")&&t.equals("3")||f.equals("3")&&t.equals("0")){costs=68;/*cost matrix for vehicle3 here*/}
            else if (f.equals("2")&&t.equals("3")||f.equals("3")&&t.equals("2")){costs=45;/*cost matrix for vehicle4 here*/}
            else if (f.equals("1")&&t.equals("3")||f.equals("3")&&t.equals("1")){costs=0;/*cost matrix for vehicle5 here*/}
            else if (f.equals("1")&&t.equals("2")||f.equals("2")&&t.equals("1")){costs=45;/*cost matrix for vehicle6 here*/}}
        else if (v.equals("vehicle6")){if(f.equals("0")&&t.equals("1")||f.equals("1")&&t.equals("0")){costs=68;}
            else if (f.equals("0")&&t.equals("2")||f.equals("2")&&t.equals("0")){costs=67;/*cost matrix for vehicle2 here*/}
            else if (f.equals("0")&&t.equals("3")||f.equals("3")&&t.equals("0")){costs=68;/*cost matrix for vehicle3 here*/}
            else if (f.equals("2")&&t.equals("3")||f.equals("3")&&t.equals("2")){costs=45;/*cost matrix for vehicle4 here*/}
            else if (f.equals("1")&&t.equals("3")||f.equals("3")&&t.equals("1")){costs=0;/*cost matrix for vehicle5 here*/}
            else if (f.equals("1")&&t.equals("2")||f.equals("2")&&t.equals("1")){costs=45;/*cost matrix for vehicle6 here*/}}
        else if (v.equals("vehicle7")){if(f.equals("0")&&t.equals("1")||f.equals("1")&&t.equals("0")){costs=68;}
            else if (f.equals("0")&&t.equals("2")||f.equals("2")&&t.equals("0")){costs=67;/*cost matrix for vehicle2 here*/}
            else if (f.equals("0")&&t.equals("3")||f.equals("3")&&t.equals("0")){costs=68;/*cost matrix for vehicle3 here*/}
            else if (f.equals("2")&&t.equals("3")||f.equals("3")&&t.equals("2")){costs=45;/*cost matrix for vehicle4 here*/}
            else if (f.equals("1")&&t.equals("3")||f.equals("3")&&t.equals("1")){costs=0;/*cost matrix for vehicle5 here*/}
            else if (f.equals("1")&&t.equals("2")||f.equals("2")&&t.equals("1")){costs=45;/*cost matrix for vehicle6 here*/}}

        
        return costs;
    }

    

    

    @Override
    public double getTransportTime(Location from, Location to, double time, Driver driver, Vehicle vehicle) {
        String f=from.getId();
        String t=to.getId();
        double tt=22;
        if (vehicle==null){tt=99999.9; return tt;}
        String v=vehicle.getId();
        /*Time Matrix to be read here*/
        if(f.equals("0")&&t.equals("1")){tt=68;}
        else if (f.equals("0")&&t.equals("2")){tt=67;/*cost matrix for vehicle2 here*/}
        else if (f.equals("0")&&t.equals("3")){tt=68;/*cost matrix for vehicle3 here*/}
        else if (f.equals("2")&&t.equals("3")){tt=45;/*cost matrix for vehicle4 here*/}
        else if (f.equals("1")&&t.equals("3")){tt=0;/*cost matrix for vehicle5 here*/}
        else if (f.equals("1")&&t.equals("2")){tt=45;/*cost matrix for vehicle6 here*/}
        return tt;
    }
    
        @Override
    public double getDistance(Location from, Location to, double departureTime, Vehicle vehicle) {

        String f=from.getId();
        String t=to.getId();
        if (vehicle==null){double d=99999; return d;}
        String v=vehicle.getId();
        /*Distance matrix to be read here*/
        double d=22;
        if(f.equals("0")&&t.equals("1")){d=40;}
        else if (f.equals("0")&&t.equals("2")){d=39;/*cost matrix for vehicle2 here*/}
        else if (f.equals("0")&&t.equals("3")){d=40;/*cost matrix for vehicle3 here*/}
        else if (f.equals("2")&&t.equals("3")){d=45;/*cost matrix for vehicle4 here*/}
        else if (f.equals("1")&&t.equals("3")){d=0;/*cost matrix for vehicle5 here*/}
        else if (f.equals("1")&&t.equals("2")){d=45;/*cost matrix for vehicle6 here*/}
        return d;}
    
}