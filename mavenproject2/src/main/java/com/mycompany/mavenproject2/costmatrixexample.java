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
package com.mycompany.mavenproject2;

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
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.util.Examples;
import au.com.bytecode.opencsv.*;
import com.graphhopper.jsprit.core.algorithm.state.InternalStates;
import com.graphhopper.jsprit.core.algorithm.state.StateManager;
import com.graphhopper.jsprit.core.problem.constraint.ConstraintManager;
import com.graphhopper.jsprit.core.problem.constraint.HardRouteConstraint;
import com.graphhopper.jsprit.core.problem.misc.JobInsertionContext;
import com.graphhopper.jsprit.core.problem.solution.SolutionCostCalculator;

import java.util.Collection;
import java.io.*;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

/**
 * Illustrates how you can use jsprit with an already compiled distance and time
 * matrix.
 *
 * @author schroeder
 */
public class costmatrixexample {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*
         * some preparation - create output folder
         */
        Examples.createOutputFolder();

        /* get a vehicle type-builder and build a type with the typeId "vehicleType" and one capacity dimension, i.e. weight, and capacity dimension value of 2
         */
        VehicleTypeImpl vehicleType1 = VehicleTypeImpl.Builder.newInstance("vehicleType1")
                .addCapacityDimension(0, 957).addCapacityDimension(1, 7200).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType2 = VehicleTypeImpl.Builder.newInstance("vehicleType2")
                .addCapacityDimension(0, 771).addCapacityDimension(1, 4800).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType3 = VehicleTypeImpl.Builder.newInstance("vehicleType3")
                .addCapacityDimension(0, 736).addCapacityDimension(1, 5600).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType4 = VehicleTypeImpl.Builder.newInstance("vehicleType4")
                .addCapacityDimension(0, 680).addCapacityDimension(1, 4800).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType5 = VehicleTypeImpl.Builder.newInstance("vehicleType5")
                .addCapacityDimension(0, 490).addCapacityDimension(1, 3600).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType6 = VehicleTypeImpl.Builder.newInstance("vehicleType6")
                .addCapacityDimension(0, 264).addCapacityDimension(1, 2400).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType7 = VehicleTypeImpl.Builder.newInstance("vehicleType7")
                .addCapacityDimension(0, 88).addCapacityDimension(1, 800).setCostPerDistance(1)
                .build();


        /*
         * get a vehicle-builder and build a vehicle located at (10,10) with type "vehicleType"
         */
        VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance("vehicle1");
        vehicleBuilder1.setStartLocation(Location.newInstance("0"));
        vehicleBuilder1.setType(vehicleType1).setReturnToDepot(false);
        VehicleImpl vehicle1 = vehicleBuilder1.build();

        VehicleImpl.Builder vehicleBuilder2 = VehicleImpl.Builder.newInstance("vehicle2");
        vehicleBuilder2.setStartLocation(Location.newInstance("0"));
        vehicleBuilder2.setType(vehicleType2).setReturnToDepot(false);
        VehicleImpl vehicle2 = vehicleBuilder2.build();

        VehicleImpl.Builder vehicleBuilder3 = VehicleImpl.Builder.newInstance("vehicle3");
        vehicleBuilder3.setStartLocation(Location.newInstance("0"));
        vehicleBuilder3.setType(vehicleType3).setReturnToDepot(false);
        VehicleImpl vehicle3 = vehicleBuilder3.build();

        VehicleImpl.Builder vehicleBuilder4 = VehicleImpl.Builder.newInstance("vehicle4");
        vehicleBuilder4.setStartLocation(Location.newInstance("0"));
        vehicleBuilder4.setType(vehicleType4).setReturnToDepot(false);
        VehicleImpl vehicle4 = vehicleBuilder4.build();

        VehicleImpl.Builder vehicleBuilder5 = VehicleImpl.Builder.newInstance("vehicle5");
        vehicleBuilder5.setStartLocation(Location.newInstance("0"));
        vehicleBuilder5.setType(vehicleType5).setReturnToDepot(false);
        VehicleImpl vehicle5 = vehicleBuilder5.build();

        VehicleImpl.Builder vehicleBuilder6 = VehicleImpl.Builder.newInstance("vehicle6");
        vehicleBuilder6.setStartLocation(Location.newInstance("0"));
        vehicleBuilder6.setType(vehicleType6).setReturnToDepot(false);
        VehicleImpl vehicle6 = vehicleBuilder6.build();

        VehicleImpl.Builder vehicleBuilder7 = VehicleImpl.Builder.newInstance("vehicle7");
        vehicleBuilder7.setStartLocation(Location.newInstance("0"));
        vehicleBuilder7.setType(vehicleType7).setReturnToDepot(false);
        VehicleImpl vehicle7 = vehicleBuilder7.build();

        FileReader r1 = new FileReader("dpmatrix18jul.csv");
        CSVReader reader = new CSVReader(r1);
        String[] nextLine;
        int a = 0;
        Service[] sm = new Service[110];
        Service s1 = Service.Builder.newInstance("0000")
                .setLocation(Location.newInstance("9999")).addTimeWindow(0, 0)
                .addSizeDimension(0, 9999).addSizeDimension(1, 9999).build();
        sm[0] = s1;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            if (a == 0) {
                System.out.println(nextLine[0] + "|" + nextLine[1] + "|" + nextLine[2] + "|" + nextLine[3]);
            } else {
                if (nextLine[4].equals("ACD")) {
                    Service s = Service.Builder.newInstance(Integer.toString(a - 1))
                            .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                            .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", "").replaceAll(" ", ""))).setName("ACD" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                    sm[a - 1] = s;
                } else if (nextLine[4].equals("MT")) {
                    Service s = Service.Builder.newInstance(Integer.toString(a - 1))
                            .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(360, 600)
                            .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", "").replaceAll(" ", ""))).setName("MT" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                    sm[a - 1] = s;
                } else if (nextLine[4].equals("Upcountry")) {
                    if (nextLine[3].equals("MT")) {
                        Service s = Service.Builder.newInstance(Integer.toString(a - 1) + 10000)
                                .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                                .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", "").replaceAll(" ", ""))).setName("MT" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                        sm[a - 1] = s;
                    } else {
                        Service s = Service.Builder.newInstance(Integer.toString(a - 1))
                                .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                                .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", "").replaceAll(" ", ""))).setName("Upcountry" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                        sm[a - 1] = s;
                    }
                } else if (nextLine[4].equals("GT")) {
                    Service s = Service.Builder.newInstance(Integer.toString(a - 1) + 1000)
                            .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                            .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1, Integer.parseInt(nextLine[1].replaceAll(",", "").replaceAll(" ", ""))).setName("GT" + "-" + nextLine[0] + "-" + nextLine[4]).build();
                    sm[a - 1] = s;
                }
            }
            a++;
        }

        /*
         * build services at the required locations, each with a capacity-demand of 1.
         */
 /*  Service service1 = Service.Builder.newInstance("1")
                .setLocation(Location.newInstance("1")).addTimeWindow(630, 1080)
                .addSizeDimension(0, 111).addSizeDimension(1, 1512).build();

        Service service2 = Service.Builder.newInstance("2")
                .setLocation(Location.newInstance("2")).addTimeWindow(630, 1080)
                .addSizeDimension(0, 119).addSizeDimension(1, 1781).build();

        Service service3 = Service.Builder.newInstance("3")
                .setLocation(Location.newInstance("3")).addTimeWindow(630, 1080)
                .addSizeDimension(0, 123).addSizeDimension(1, 2514).build();
        /*
		/*
         * Assume the following symmetric distance-matrix
		 * from,to,distance
		 * 0,1,10.0
		 * 0,2,20.0
		 * 0,3,5.0
		 * 1,2,4.0
		 * 1,3,1.0
		 * 2,3,2.0
		 *
		 * and this time-matrix
		 * 0,1,5.0
		 * 0,2,10.0
		 * 0,3,2.5
		 * 1,2,2.0
		 * 1,3,0.5
		 * 2,3,1.0
         */
        //define a matrix-builder building a symmetric matrix
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        int p = 0;
        while (true) {
            vrpBuilder.addJob(sm[p]);
            if (p == 109) {
                break;
            }
            p++;
        }

        VehicleRoutingTransportCosts routingCosts = new cmtx(vrpBuilder.getLocations()); //which is the default VehicleRoutingTransportCosts in builder above
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
        FileReader r2 = new FileReader("cdtmatrix_tmodimpl.csv");
        CSVReader reader2 = new CSVReader(r2);
        final List<String[]> myEntries2 = reader2.readAll();
        System.out.print(myEntries2.get(1)[2]);
        HardRouteConstraint accessConstraint = new HardRouteConstraint() {

            @Override
            public boolean fulfilled(JobInsertionContext iContext) {
                int a = iContext.getRoute().getActivities().size();
                String v = iContext.getRoute().getVehicle().getId();
                if (iContext.getJob().getName().split("-")[0].equals("MT")) {
                    return true;
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
        /*
HardRouteConstraint tmodConstraint = new HardRouteConstraint() {

    @Override
    public boolean fulfilled(JobInsertionContext iContext) {
    int a=iContext.getRoute().getActivities().size();
    if (a==3){String t=iContext.getRoute().getActivities().get(2).getLocation().getId();
              String j=iContext.getJob().getName().split("-")[1];
            int i=1;
            double ctv1=0;
            while(true){if(t.equals(myEntries2.get(i)[1]))
                              { break;}
            i++;
            if(i==41){break;}}
            int k=1;
            double cjv1=0;
            while(true){if(j.equals(myEntries2.get(k)[1]))
                              { break;}
            k++;
            if(k==41){break;}}
            if (k>i){return false;}
           }
    else if (a==2){

            String t=iContext.getRoute().getActivities().get(1).getLocation().getId();
            String j=iContext.getJob().getName().split("-")[1];
            int i=1;
            double ctv1=0;
            while(true){if(t.equals(myEntries2.get(i)[1]))
                              { break;}
            i++;
            if(i==41){break;}}
            int k=1;
            double cjv1=0;
            while(true){if(j.equals(myEntries2.get(k)[1]))
                              { break;}
            k++;
            if(k==41){break;}}
            if (k>i){return false;}
}
    else if (a==1){String t=iContext.getRoute().getActivities().get(0).getLocation().getId();
              String j=iContext.getJob().getName().split("-")[1];
            int i=1;
            double ctv1=0;
            while(true){if(t.equals(myEntries2.get(i)[1]))
                              { break;}
            i++;
            if(i==41){break;}}
            int k=1;
            double cjv1=0;
            while(true){if(j.equals(myEntries2.get(k)[1]))
                              { break;}
            k++;
            if(k==41){break;}}
            if (k>i){return false;}
               }
    return true;}

};*/

        constraintManager.addConstraint(accessConstraint);
//constraintManager.addConstraint(tmodConstraint);

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
                    /* int a=route.getActivities().size();
                                        if (a==4){String t=route.getActivities().get(3).getLocation().getId();
                                                  String j=route.getActivities().get(2).getLocation().getId();
                                                  int i=1;
                                                  double ct=0;
                                                  while(true){if(t.equals(myEntries2.get(i)[1]))
                                                                          {ct=Integer.parseInt(myEntries2.get(i)[2]); break;}
                                                              i++;
                                                              if(i==41){break;}}
                                                  int k=1;
                                                  double cj=0;
                                                  while(true){if(j.equals(myEntries2.get(k)[1]))
                                                                          {cj=Integer.parseInt(myEntries2.get(i)[2]); break;}
                                                              k++;
                                                              if(k==41){break;}}
                                                  if (cj>ct){costs-=ct; costs+=cj;}
                                                  if (ct>cj){costs-=cj; costs+=ct;}
                                                  }
                                        if (a==3){String t=route.getActivities().get(2).getLocation().getId();
                                                  String j=route.getActivities().get(1).getLocation().getId();
                                                  int i=1;
                                                  double ct=0;
                                                  while(true){if(t.equals(myEntries2.get(i)[1]))
                                                                          {ct=Integer.parseInt(myEntries2.get(i)[2]); break;}
                                                              i++;
                                                              if(i==41){break;}}
                                                  int k=1;
                                                  double cj=0;
                                                  while(true){if(j.equals(myEntries2.get(k)[1]))
                                                                          {cj=Integer.parseInt(myEntries2.get(i)[2]); break;}
                                                              k++;
                                                              if(k==41){break;}}
                                                  if (cj>ct){costs-=ct; costs+=cj;}
                                                  if (ct>cj){costs-=cj; costs+=ct;}
                                                  }
    else if (a==2){String t=route.getActivities().get(1).getLocation().getId();
                                                  String j=route.getActivities().get(0).getLocation().getId();
                                                  int i=1;
                                                  double ct=0;
                                                  while(true){if(t.equals(myEntries2.get(i)[1]))
                                                                          {ct=Integer.parseInt(myEntries2.get(i)[2]); break;}
                                                              i++;
                                                              if(i==41){break;}}
                                                  int k=1;
                                                  double cj=0;
                                                  while(true){if(j.equals(myEntries2.get(k)[1]))
                                                                          {cj=Integer.parseInt(myEntries2.get(i)[2]); break;}
                                                              k++;
                                                              if(k==41){break;}}
                                                  if (cj>ct){costs-=ct; costs+=cj;}
                                                  if (ct>cj){costs-=cj; costs+=ct;}
                                                  }*/
                }

                return costs;
            }
        };
        vraBuilder.addCoreStateAndConstraintStuff(true)
                .setProperty(Jsprit.Parameter.FAST_REGRET, "true").setProperty(Jsprit.Parameter.THREADS, "3");
        VehicleRoutingAlgorithm vra = vraBuilder.buildAlgorithm();
        vra.setMaxIterations(2000);

        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();

        VehicleRoutingProblemSolution solution = new SelectBest().selectSolution(solutions);

        /*
         * print solution
         */
        SolutionPrinter.print(solution);
        TransportDistance dist = new cmtx(vrpBuilder.getLocations());
        SolutionAnalyser analyser = new SolutionAnalyser(problem, solution, dist);
        CSVWriter writer = new CSVWriter(new FileWriter("out2.csv"), '\t');
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

//        new Plotter(problem, Solutions.bestOf(solutions)).plot("output/yo.png", "po");
    }

}
