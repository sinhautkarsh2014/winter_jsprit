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
package com.mycompany.startup_proj;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.algorithm.selector.SelectBest;
import com.graphhopper.jsprit.core.analysis.SolutionAnalyser;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.cost.TransportDistance;
import com.graphhopper.jsprit.core.problem.cost.VehicleRoutingTransportCosts;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.solution.route.VehicleRoute;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TourActivity;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.problem.vehicle.Vehicle;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.util.Examples;
import au.com.bytecode.opencsv.*;
import com.graphhopper.jsprit.core.algorithm.state.InternalStates;
import com.graphhopper.jsprit.core.algorithm.state.StateManager;
import com.graphhopper.jsprit.core.problem.constraint.ConstraintManager;
import com.graphhopper.jsprit.core.problem.constraint.HardRouteConstraint;
import com.graphhopper.jsprit.core.problem.misc.JobInsertionContext;
import com.graphhopper.jsprit.core.problem.solution.SolutionCostCalculator;
import com.graphhopper.jsprit.io.problem.VrpXMLWriter;

import java.util.Collection;
import java.io.*;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Illustrates how you can use jsprit for brittania problem.
 *
 * @author utkarsh sinha
 */
public class UL_main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*
         * some preparation - create output folder
         */
        Examples.createOutputFolder();

        /* get a vehicle type-builder and build a type with the typeId "vehicleType" and one capacity dimension, i.e. weight, and capacity dimension value of 2
         */
        VehicleTypeImpl E1 = VehicleTypeImpl.Builder.newInstance("E1")
                .addCapacityDimension(0, 28000).build();
        VehicleTypeImpl E2 = VehicleTypeImpl.Builder.newInstance("E2")
                .addCapacityDimension(0, 22500).build();
        VehicleTypeImpl E3 = VehicleTypeImpl.Builder.newInstance("E3")
                .addCapacityDimension(0, 29000).build();
        VehicleTypeImpl E4 = VehicleTypeImpl.Builder.newInstance("E4")
                .addCapacityDimension(0, 5500).build();
        


        /*Build vehicles using vehicle types
         */
        
        /*Location of depots*/
        String[] depots ={"80121","67050","26013","20098","57100","41100","12011","4014","40017","42025","41011","63037","45030","63035","88040","20043","47023","60012","64024"};
        
        /*Collection of all vehicles*/
        Collection<Vehicle> allvehicles=new ArrayList<Vehicle>();
        
        /*E1-Type Builder loop*/
        String[] serviceproviders_E1 = new String[7];
        serviceproviders_E1[0]="T1087356";
        serviceproviders_E1[1]="T1105438";
        serviceproviders_E1[2]="T18833";
        serviceproviders_E1[3]="T201601";
        serviceproviders_E1[4]="T204767";
        serviceproviders_E1[5]="T216130";
        serviceproviders_E1[6]="T50283698";
        
        for (int i=0; i<19; i++)
        {
            for (int j=0; j<7; j++){
                VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance(serviceproviders_E1[j]+"_"+"E1");
                vehicleBuilder1.setStartLocation(Location.newInstance(depots[i]));
                vehicleBuilder1.setType(E1).setReturnToDepot(false);
                VehicleImpl vehicle1 = vehicleBuilder1.build();
                allvehicles.add(vehicle1);
            }
        
        }
        
        /*E2-Type Builder loop*/
        
        String[] serviceproviders_E2 = new String[6];
        serviceproviders_E2[0]="T1087356";
        serviceproviders_E2[1]="T1105438";
        serviceproviders_E2[2]="T201601";
        serviceproviders_E2[3]="T204767";
        serviceproviders_E2[4]="T216130";
        serviceproviders_E2[5]="T50283698";
        
        for (int i=0; i<19; i++)
        {
            for (int j=0; j<6; j++){
                VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance(serviceproviders_E2[j]+"_"+"E2");
                vehicleBuilder1.setStartLocation(Location.newInstance(depots[i]));
                vehicleBuilder1.setType(E2).setReturnToDepot(false);
                VehicleImpl vehicle1 = vehicleBuilder1.build();
                allvehicles.add(vehicle1);
            }
        
        }
        
        /*E3-Type Builder loop*/
        
        String[] serviceproviders_E3 = new String[1];
        serviceproviders_E3[0]="T18833";
        
        
        for (int i=0; i<19; i++)
        {
            for (int j=0; j<1; j++){
                VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance(serviceproviders_E3[j]+"_"+"E3");
                vehicleBuilder1.setStartLocation(Location.newInstance(depots[i]));
                vehicleBuilder1.setType(E3).setReturnToDepot(false);
                VehicleImpl vehicle1 = vehicleBuilder1.build();
                allvehicles.add(vehicle1);
            }
        
        }
        
        /*E4-Type Builder loop*/
        
        String[] serviceproviders_E4 = new String[1];
        serviceproviders_E4[0]="T204767";
                
        for (int i=0; i<19; i++)
        {
            for (int j=0; j<1; j++){
                VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance(serviceproviders_E4[j]+"_"+"E4");
                vehicleBuilder1.setStartLocation(Location.newInstance(depots[i]));
                vehicleBuilder1.setType(E4).setReturnToDepot(false);
                VehicleImpl vehicle1 = vehicleBuilder1.build();
                allvehicles.add(vehicle1);
            }
        
        }

       
        /*
        *** Reading the demands and the category types
        */
        FileReader r1 = new FileReader("brtittest1.csv");
        CSVReader reader = new CSVReader(r1);
        String[] nextLine;
        int a = 0;
        /* Define an array of Services*/
        Service[] sm = new Service[70];
        /*Initializing the array*/
        Service s1 = Service.Builder.newInstance("0000")
                .setLocation(Location.newInstance("9999")).addTimeWindow(0, 0)
                .addSizeDimension(0, 9999).addSizeDimension(1, 9999).build();
        sm[0] = s1;
        /*A loop to read all the orders*/
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            if (a == 0) {
                System.out.println(nextLine[0] + "|" + nextLine[1] + "|" + nextLine[2] + "|" + nextLine[3]);
            } else {
                if (nextLine[4].equals("ACD")) {
                    Service s = Service.Builder.newInstance(Integer.toString(a - 1))
                            .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                            .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", ""))).setName("ACD" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                    sm[a - 1] = s;
                } else if (nextLine[4].equals("MT")) {
                    Service s = Service.Builder.newInstance(Integer.toString(a - 1))
                            .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(360, 600)
                            .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", ""))).setName("MT" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                    sm[a - 1] = s;
                } else if (nextLine[4].equals("Upcountry")) {
                    if (nextLine[3].equals("MT")) {
                        Service s = Service.Builder.newInstance(Integer.toString(a - 1) + 10000)
                                .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                                .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", ""))).setName("MT" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                        sm[a - 1] = s;
                    } else {
                        Service s = Service.Builder.newInstance(Integer.toString(a - 1))
                                .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                                .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", ""))).setName("Upcountry" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                        sm[a - 1] = s;
                    }
                } else if (nextLine[4].equals("GT")) {
                    Service s = Service.Builder.newInstance(Integer.toString(a - 1) + 1000)
                            .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                            .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", ""))).setName("GT" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                    sm[a - 1] = s;
                }
            }
            a++;
        }

       
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        /*Adding all the services to the problem; 69=nservices-1*/
        int p = 0;
        while (true) {
            vrpBuilder.addJob(sm[p]);
            if (p == 69) {
                break;
            }
            p++;
        }

        VehicleRoutingTransportCosts routingCosts = new cmtx2(vrpBuilder.getLocations()); //which is the default VehicleRoutingTransportCosts in builder above
        vrpBuilder.setRoutingCost(routingCosts);

        vrpBuilder.addVehicle(vehicle1).addVehicle(vehicle2).addVehicle(vehicle3).addVehicle(vehicle4).addVehicle(vehicle5).addVehicle(vehicle6).addVehicle(vehicle7);

        // vrpBuilder.setRoutingCost(new MultiVehTypeCosts(vrpBuilder.getLocations()));
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.INFINITE);
        VehicleRoutingProblem problem = vrpBuilder.build();

        Jsprit.Builder vraBuilder = Jsprit.Builder.newInstance(problem);

        final StateManager stateManager = new StateManager(problem);
        stateManager.updateLoadStates();
        stateManager.updateTimeWindowStates();
        ConstraintManager constraintManager = new ConstraintManager(problem, stateManager);
        
        HardRouteConstraint accessConstraint = new HardRouteConstraint() {

            @Override
            public boolean fulfilled(JobInsertionContext iContext) {
                int a = iContext.getRoute().getActivities().size();
                String v = iContext.getRoute().getVehicle().getId();
                if (iContext.getJob().getName().split("-")[0].equals("MT")) {
                    return true;
                }
                if (a == 7) {
                    String[] actids= new String[8];
                    actids[0]=iContext.getRoute().getActivities().get(0).getLocation().getId();
                    actids[1]=iContext.getRoute().getActivities().get(1).getLocation().getId();
                    actids[2]=iContext.getRoute().getActivities().get(2).getLocation().getId();
                    actids[3]=iContext.getRoute().getActivities().get(3).getLocation().getId();
                    actids[4]=iContext.getRoute().getActivities().get(4).getLocation().getId();
                    actids[5]=iContext.getRoute().getActivities().get(5).getLocation().getId();
                    actids[6]=iContext.getRoute().getActivities().get(6).getLocation().getId();
                    actids[7]=iContext.getJob().getName().split("-")[1];
                    List<String> actidsl = Arrays.asList(actids); 
                    int freq=Collections.frequency(actidsl, actids[0])+Collections.frequency(actidsl, actids[1])+Collections.frequency(actidsl, actids[2])+Collections.frequency(actidsl, actids[3])+Collections.frequency(actidsl, actids[4])+Collections.frequency(actidsl, actids[5])+Collections.frequency(actidsl, actids[6])+Collections.frequency(actidsl, actids[7]);
                    if (freq>=32){return true;}
                    else {return false;}

                    }
                if (a == 6) {
                    String[] actids= new String[7];
                    actids[0]=iContext.getRoute().getActivities().get(0).getLocation().getId();
                    actids[1]=iContext.getRoute().getActivities().get(1).getLocation().getId();
                    actids[2]=iContext.getRoute().getActivities().get(2).getLocation().getId();
                    actids[3]=iContext.getRoute().getActivities().get(3).getLocation().getId();
                    actids[4]=iContext.getRoute().getActivities().get(4).getLocation().getId();
                    actids[5]=iContext.getRoute().getActivities().get(5).getLocation().getId();
                    actids[6]=iContext.getJob().getName().split("-")[1];
                    List<String> actidsl = Arrays.asList(actids); 
                    int freq=Collections.frequency(actidsl, actids[0])+Collections.frequency(actidsl, actids[1])+Collections.frequency(actidsl, actids[2])+Collections.frequency(actidsl, actids[3])+Collections.frequency(actidsl, actids[4])+Collections.frequency(actidsl, actids[5])+Collections.frequency(actidsl, actids[6]);
                    if (freq>=25){return true;}
                    else {return false;}

                    }
                if (a == 5) {
                    String[] actids= new String[6];
                    actids[0]=iContext.getRoute().getActivities().get(0).getLocation().getId();
                    actids[1]=iContext.getRoute().getActivities().get(1).getLocation().getId();
                    actids[2]=iContext.getRoute().getActivities().get(2).getLocation().getId();
                    actids[3]=iContext.getRoute().getActivities().get(3).getLocation().getId();
                    actids[4]=iContext.getRoute().getActivities().get(4).getLocation().getId();
                    actids[5]=iContext.getJob().getName().split("-")[1];
                    List<String> actidsl = Arrays.asList(actids); 
                    int freq=Collections.frequency(actidsl, actids[0])+Collections.frequency(actidsl, actids[1])+Collections.frequency(actidsl, actids[2])+Collections.frequency(actidsl, actids[3])+Collections.frequency(actidsl, actids[4])+Collections.frequency(actidsl, actids[5]);
                    if (freq>=18){return true;}
                    else {return false;}

                    }
                if (a == 4) {
                    String[] actids= new String[5];
                    actids[0]=iContext.getRoute().getActivities().get(0).getLocation().getId();
                    actids[1]=iContext.getRoute().getActivities().get(1).getLocation().getId();
                    actids[2]=iContext.getRoute().getActivities().get(2).getLocation().getId();
                    actids[3]=iContext.getRoute().getActivities().get(3).getLocation().getId();
                    actids[4]=iContext.getJob().getName().split("-")[1];
                    List<String> actidsl = Arrays.asList(actids); 
                    int freq=Collections.frequency(actidsl, actids[0])+Collections.frequency(actidsl, actids[1])+Collections.frequency(actidsl, actids[2])+Collections.frequency(actidsl, actids[3])+Collections.frequency(actidsl, actids[4]);
                    if (freq>=13){return true;}
                    else {return false;}

}

                if (a == 3) {
                    if (iContext.getRoute().getActivities().get(0).getLocation().getId().equals(iContext.getJob().getName().split("-")[1]) && iContext.getRoute().getActivities().get(1).getLocation().getId().equals(iContext.getRoute().getActivities().get(2).getLocation().getId())) {
                        return true;
                    }
                    if (iContext.getRoute().getActivities().get(1).getLocation().getId().equals(iContext.getJob().getName().split("-")[1]) && iContext.getRoute().getActivities().get(0).getLocation().getId().equals(iContext.getRoute().getActivities().get(2).getLocation().getId())) {
                        return true;
                    }
                    if (iContext.getRoute().getActivities().get(2).getLocation().getId().equals(iContext.getJob().getName().split("-")[1]) && iContext.getRoute().getActivities().get(1).getLocation().getId().equals(iContext.getRoute().getActivities().get(1).getLocation().getId())) {
                        return true;
                    }
                    if (iContext.getRoute().getActivities().get(0).getLocation().getId().equals(iContext.getJob().getName().split("-")[1]) && iContext.getRoute().getActivities().get(1).getLocation().getId().equals(iContext.getRoute().getActivities().get(2).getLocation().getId()) && iContext.getRoute().getActivities().get(0).getLocation().getId().equals(iContext.getRoute().getActivities().get(2).getLocation().getId())) {
                        return true;
                    }

                }
                if (a == 2) {
                    if (!iContext.getRoute().getActivities().get(0).getLocation().getId().equals(iContext.getJob().getName().split("-")[1]) && !iContext.getRoute().getActivities().get(0).getLocation().getId().equals(iContext.getJob().getName().split("-")[1])) {
                        return false;
                    }

                }

                if (a >= 3) {
                    return false;
                } else if (a < 3) {
                    return true;
                }
                return false;
            }

        };
       

        constraintManager.addConstraint(accessConstraint);

        constraintManager.addLoadConstraint();
        constraintManager.addTimeWindowConstraint();

        vraBuilder.setStateAndConstraintManager(stateManager, constraintManager);
        /*this cost calculator can be used to impose rewards and penalties*/

        SolutionCostCalculator costCalculator = new SolutionCostCalculator() {
            @Override
            public double getCosts(VehicleRoutingProblemSolution solution) {
                double costs = 0.;
                for (VehicleRoute route : solution.getRoutes()) {
                    costs += route.getVehicle().getType().getVehicleCostParams().fix;
                    costs += stateManager.getRouteState(route, InternalStates.COSTS, Double.class);
//					
                    
                }

                return costs;
            }
        };
        vraBuilder.addCoreStateAndConstraintStuff(true)
                .setProperty(Jsprit.Parameter.FAST_REGRET, "true").setProperty(Jsprit.Parameter.THREADS, "4");
        VehicleRoutingAlgorithm vra = vraBuilder.buildAlgorithm();
        vra.setMaxIterations(100);

        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();

        VehicleRoutingProblemSolution solution = new SelectBest().selectSolution(solutions);
        Collection<VehicleRoutingProblemSolution> best_solution_collection = new ArrayList<VehicleRoutingProblemSolution>();
        best_solution_collection.add(solution);        
        /*
         * print solution
         */
        /*Collection<VehicleRoute> initroutes=solution.getRoutes();
        List<VehicleRoute> list = new ArrayList<VehicleRoute>(initroutes);*/
        new VrpXMLWriter(problem, best_solution_collection).write("incre.xml");
        
        /*FileOutputStream fout = new FileOutputStream("/home/utkarsh/jsprit/startup_proj/init.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(list);
        fout.close();
        oos.close();*/
        SolutionPrinter.print(solution);
        TransportDistance dist = new cmtx2(vrpBuilder.getLocations());
        SolutionAnalyser analyser = new SolutionAnalyser(problem, solution, dist);
        CSVWriter writer = new CSVWriter(new FileWriter("out3.csv"), '\t');
        // feed in your array (or convert your data to an array)
        String[] entry1 = "shipping_id#party_id#truck_type_used#weight_demand#volume_demand#weight_load_taken#vehicle_weight_capacity#volume_load_taken#vehicle_volume_capacity".split("#");
        writer.writeNext(entry1);
        int i = 0;
        for (VehicleRoute route : solution.getRoutes()) {
            System.out.println("------");
            System.out.println("vehicleId: " + route.getVehicle().getId());
            System.out.println("load@beginning: " + analyser.getLoadAtBeginning(route));
            System.out.println("load@end: " + analyser.getLoadAtEnd(route));
            System.out.println("no. of deliveries done by the vehicle: " + analyser.getNumberOfDeliveriesAtEnd(route));
            System.out.println("vehicle capacity: " + route.getVehicle().getType().getCapacityDimensions());
            System.out.println("route id :" + route.toString());
            i++;
            for (TourActivity act : route.getActivities()) {
                System.out.println("--");
                System.out.println("actType: " + act.getName() + " demand: " + act.getSize());
                System.out.println("load(before)@" + act.getLocation().getId() + ": " + analyser.getLoadJustBeforeActivity(act, route));
                System.out.println("load(after)@" + act.getLocation().getId() + ": " + analyser.getLoadRightAfterActivity(act, route));
                System.out.println("transportCosts@" + act.getLocation().getId() + ": " + analyser.getVariableTransportCostsAtActivity(act, route));
                System.out.println("capViolation(after)@" + act.getLocation().getId() + ": " + analyser.getCapacityViolationAfterActivity(act, route));
                System.out.println("timeWindowViolation@" + act.getLocation().getId() + ": " + analyser.getTimeWindowViolationAtActivity(act, route));
                System.out.println("time from last activity@" + act.getLocation().getId() + ": " + analyser.getLastTransportTimeAtActivity(act, route));
                String[] entries = new String[10];
                entries[0] = Integer.toString(i);
                entries[1] = act.getLocation().getId();
                entries[2] = route.getVehicle().getId();
                entries[3] = act.getSize().toString().split("]")[5].split("=")[1];
                entries[4] = act.getSize().toString().split("]")[2].split("=")[1];
                entries[5] = analyser.getLoadAtEnd(route).toString().split("]")[5].split("=")[1];
                entries[6] = route.getVehicle().getType().getCapacityDimensions().toString().split("]")[5].split("=")[1];
                entries[7] = analyser.getLoadAtEnd(route).toString().split("]")[2].split("=")[1];
                entries[8] = route.getVehicle().getType().getCapacityDimensions().toString().split("]")[2].split("=")[1];
                entries[9] = analyser.getVariableTransportCostsAtActivity(act, route).toString();
                writer.writeNext(entries);
            }
        }
        writer.close();

    }

}