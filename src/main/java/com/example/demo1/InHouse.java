    package com.example.demo1;
    // this class inherits only the Part class methods and properties.
    public class InHouse extends Part {

        //class contents go here
        //class definition-->defining the object's variables(instance variables/object fields/object attributes)
        // that belong to them
        private int machineId;
            //creation the InHouse constructor that inherits fields from the abstract/superclass as its parameters.
        public  InHouse(int id, String name, double price, int stock, int min, int max, int machineId ) {
            super(id, name, price, stock, min, max);// call of the Part class
            this.machineId = machineId;
        }


        /**
         * @return the machineId
         */
        public int getMachineId() {
            return machineId;
        }

        /**
         * @param machineId the machineId to set
         */
        public void setMachineId(int machineId) {
            this.machineId = machineId;
        }


    }

