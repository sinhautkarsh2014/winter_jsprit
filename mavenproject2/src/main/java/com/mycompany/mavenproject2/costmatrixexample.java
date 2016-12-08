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
import com.graphhopper.jsprit.analysis.toolbox.GraphStreamViewer;
import com.graphhopper.jsprit.analysis.toolbox.Plotter;
import com.graphhopper.jsprit.core.util.Solutions;


import java.util.Collection;
import java.io.*;
import java.text.NumberFormat;
import java.lang.Number;

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
                .addCapacityDimension(0, 1196).addCapacityDimension(1, 9000).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType2 = VehicleTypeImpl.Builder.newInstance("vehicleType2")
                .addCapacityDimension(0, 964).addCapacityDimension(1, 6000).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType3 = VehicleTypeImpl.Builder.newInstance("vehicleType3")
                .addCapacityDimension(0, 920).addCapacityDimension(1, 7000).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType4 = VehicleTypeImpl.Builder.newInstance("vehicleType4")
                .addCapacityDimension(0, 850).addCapacityDimension(1, 6000).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType5 = VehicleTypeImpl.Builder.newInstance("vehicleType5")
                .addCapacityDimension(0, 613).addCapacityDimension(1, 4500).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType6 = VehicleTypeImpl.Builder.newInstance("vehicleType6")
                .addCapacityDimension(0, 330).addCapacityDimension(1, 3000).setCostPerDistance(1)
                .build();
        VehicleTypeImpl vehicleType7 = VehicleTypeImpl.Builder.newInstance("vehicleType7")
                .addCapacityDimension(0, 110).addCapacityDimension(1, 1000).setCostPerDistance(1)
                .build();


        /*
         * get a vehicle-builder and build a vehicle located at (10,10) with type "vehicleType"
         */
        VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance("vehicle1");
        vehicleBuilder1.setStartLocation(Location.newInstance("0"));
        vehicleBuilder1.setType(vehicleType1);
        VehicleImpl vehicle1 = vehicleBuilder1.build();

        VehicleImpl.Builder vehicleBuilder2 = VehicleImpl.Builder.newInstance("vehicle2");
        vehicleBuilder2.setStartLocation(Location.newInstance("0"));
        vehicleBuilder2.setType(vehicleType2);
        VehicleImpl vehicle2 = vehicleBuilder2.build();

        VehicleImpl.Builder vehicleBuilder3 = VehicleImpl.Builder.newInstance("vehicle3");
        vehicleBuilder3.setStartLocation(Location.newInstance("0"));
        vehicleBuilder3.setType(vehicleType3);
        VehicleImpl vehicle3 = vehicleBuilder3.build();

        VehicleImpl.Builder vehicleBuilder4 = VehicleImpl.Builder.newInstance("vehicle4");
        vehicleBuilder4.setStartLocation(Location.newInstance("0"));
        vehicleBuilder4.setType(vehicleType4);
        VehicleImpl vehicle4 = vehicleBuilder4.build();

        VehicleImpl.Builder vehicleBuilder5 = VehicleImpl.Builder.newInstance("vehicle5");
        vehicleBuilder5.setStartLocation(Location.newInstance("0"));
        vehicleBuilder5.setType(vehicleType5);
        VehicleImpl vehicle5 = vehicleBuilder5.build();

        VehicleImpl.Builder vehicleBuilder6 = VehicleImpl.Builder.newInstance("vehicle6");
        vehicleBuilder6.setStartLocation(Location.newInstance("0"));
        vehicleBuilder6.setType(vehicleType6);
        VehicleImpl vehicle6 = vehicleBuilder6.build();

        VehicleImpl.Builder vehicleBuilder7 = VehicleImpl.Builder.newInstance("vehicle7");
        vehicleBuilder7.setStartLocation(Location.newInstance("0"));
        vehicleBuilder7.setType(vehicleType7);
        VehicleImpl vehicle7 = vehicleBuilder7.build();

        FileReader r1 = new FileReader("brittest1.csv");
        CSVReader reader = new CSVReader(r1);
        String[] nextLine;
        int a = 0;
        Service[] sm=new Service[70];
        Service s1 = Service.Builder.newInstance("0000")
                .setLocation(Location.newInstance("9999")).addTimeWindow(0, 0)
                .addSizeDimension(0, 9999).addSizeDimension(1,9999 ).build();
        sm[0]=s1;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            if (a==0){System.out.println(nextLine[0] + "|" + nextLine[1] + "|" + nextLine[2] + "|" + nextLine[3]);
            }
            else
            {if(nextLine[3].equals("AW")){Service s = Service.Builder.newInstance(Integer.toString(a-1))
                .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1,Integer.parseInt(nextLine[1].replaceAll(",", "")) ).build();
                sm[a-1]=s;
            }
            else if(nextLine[3].equals("MT")){Service s = Service.Builder.newInstance(Integer.toString(a-1))
                .setLocation(Location.newInstance(nextLine[0])).addTimeWindow(630, 1080)
                .addSizeDimension(0, Integer.parseInt(nextLine[2])).addSizeDimension(1,Integer.parseInt(nextLine[1].replaceAll(",", "")) ).build();
                  sm[a-1]=s;    }
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
        int p=0;
        while(true){
        vrpBuilder.addJob(sm[p]);
                if(p==69){break;}
        p++;}

        VehicleRoutingTransportCosts routingCosts = new cmtx(vrpBuilder.getLocations()); //which is the default VehicleRoutingTransportCosts in builder above
        vrpBuilder.setRoutingCost(routingCosts);

        vrpBuilder.addVehicle(vehicle1).addVehicle(vehicle2).addVehicle(vehicle3).addVehicle(vehicle4).addVehicle(vehicle5).addVehicle(vehicle6).addVehicle(vehicle7);

        // vrpBuilder.setRoutingCost(new MultiVehTypeCosts(vrpBuilder.getLocations()));
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.INFINITE);
        VehicleRoutingProblem problem = vrpBuilder.build();
        VehicleRoutingAlgorithm vra = Jsprit.createAlgorithm(problem);

        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();

        VehicleRoutingProblemSolution solution = new SelectBest().selectSolution(solutions);

        /*
         * print solution
         */
        SolutionPrinter.print(solution);
        TransportDistance dist = new cmtx(vrpBuilder.getLocations());
        SolutionAnalyser analyser = new SolutionAnalyser(problem, solution, dist);
        for (VehicleRoute route : solution.getRoutes()) {
            System.out.println("------");
            System.out.println("vehicleId: " + route.getVehicle().getId());
            System.out.println("load@beginning: " + analyser.getLoadAtBeginning(route));
            System.out.println("load@end: " + analyser.getLoadAtEnd(route));
            for (TourActivity act : route.getActivities()) {
                System.out.println("--");
                System.out.println("actType: " + act.getName() + " demand: " + act.getSize());
                System.out.println("load(before)@" + act.getLocation().getId() + ": " + analyser.getLoadJustBeforeActivity(act, route));
                System.out.println("load(after)@" + act.getLocation().getId() + ": " + analyser.getLoadRightAfterActivity(act, route));
                System.out.println("transportCosts@" + act.getLocation().getId() + ": " + analyser.getVariableTransportCostsAtActivity(act, route));
                System.out.println("capViolation(after)@" + act.getLocation().getId() + ": " + analyser.getCapacityViolationAfterActivity(act, route));
                System.out.println("timeWindowViolation@" + act.getLocation().getId() + ": " + analyser.getTimeWindowViolationAtActivity(act, route));
            }
        }

//        new Plotter(problem, Solutions.bestOf(solutions)).plot("output/yo.png", "po");
    }

}
