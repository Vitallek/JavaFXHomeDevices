package com.example.javafxhomedevices.Apartment;

import com.example.javafxhomedevices.customExceptions.NoFreeSockets;
import com.example.javafxhomedevices.customExceptions.OverPower;
import com.example.javafxhomedevices.customExceptions.SocketIsAleadyTaken;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ApartmentMain {

    private Apartment apartment;
    private ArrayList<Room> allRooms;
    private ArrayList<Socket> allSockets;
    private ArrayList<Device> allDevices;
    private String fileName;
    final String apartmentName = "r6siege";

    public ApartmentMain(String apartmentName) {
        this.apartment = new Apartment(apartmentName);
        this.allRooms = new ArrayList<Room>();
        this.allSockets = new ArrayList<Socket>();
        this.allDevices = new ArrayList<Device>();
        this.fileName = apartmentName;
    }
    public ArrayList<Room> getAllRooms() {
        return allRooms;
    }
    public ArrayList<Socket> getAllSockets() {
        return allSockets;
    }
    public ArrayList<Device> getAllDevices() {
        return allDevices;
    }
    public String getFileName() {
        return fileName;
    }

    /*
    * creates 1 apartment with 2 rooms
    * 1 socket in each room
    * 1 device in each socket
    *
    * */
    public void startConfig(){
        apartment = new Apartment(apartmentName);
        allRooms.clear();
        allSockets.clear();
        allDevices.clear();

        Room defaultRoom1 = new Room("DefaultRoom1");
        Room defaultRoom2 = new Room("DefaultRoom2");
        apartment.addRoomToAppartment(defaultRoom1);
        apartment.addRoomToAppartment(defaultRoom2);
        allRooms.add(defaultRoom1);
        allRooms.add(defaultRoom2);

        Socket defaultSocket1 = new Socket("DefaultSocket1", defaultRoom1);
        Socket defaultSocket2 = new Socket("DefaultSocket2", defaultRoom2);
        defaultRoom1.addSocketToRoom(defaultSocket1);
        defaultRoom2.addSocketToRoom(defaultSocket2);
        allSockets.add(defaultSocket1);
        allSockets.add(defaultSocket2);

        Device defaultDevice1 = new Device("DefaultDevice1", 130, defaultSocket1, true, defaultRoom1);
        Device defaultDevice2 = new Device("DefaultDevice2", 260, defaultSocket2, false, defaultRoom2);
        defaultRoom1.addDeviceToRoom(defaultDevice1);
        defaultRoom2.addDeviceToRoom(defaultDevice2);
        defaultSocket1.addDeviceToSocket(defaultDevice1);
        defaultSocket2.addDeviceToSocket(defaultDevice2);
        allDevices.add(defaultDevice1);
        allDevices.add(defaultDevice2);

        System.out.println("Successful. ");
    }

    /*
    * prints list of options
    *
    * */
//    private void startMenu(String[] options){
//        for (String option : options){
//            System.out.println(option);
//        }
//    }
//
//    public void main(String[] args) {
//
//        apartment = new Apartment(apartmentName);
//        System.out.print("Welcome to " + apartment + "\n Select option: \n");
//
//        allRooms = new ArrayList<>();
//        allSockets = new ArrayList<>();
//        allDevices = new ArrayList<>();
//        String[] options = {"1. Add Room",
//                "2. Room list",
//                "3. Add socket to room",
//                "4. Socket list",
//                "5. Add device",
//                "6. Delete device",
//                "7. Device list",
//                "8. Turn ON device",
//                "9. Turn OFF device",
//                "10. Sort devices by power",
//                "11. Show total power",
//                "12. Find device by name",
//                "101. Save config to file",
//                "102. Load config from file",
//                "103. Load default config from inside",
//                "0. Exit",
//        };
//        Scanner scanner = new Scanner(System.in);
//        int option = 1;
//
//        while (option != 0){
//            startMenu(options);
//            try{
//                option = Integer.parseInt(scanner.nextLine());
//                switch (option) {
//                    case 1: addRoom(scanner); break;
//                    case 2: showRooms(); break;
//                    case 3: addSocket(scanner); break;
//                    case 4: showSockets(); break;
//                    case 5: addDevice(scanner); break;
//                    case 6: removeDevice(scanner); break;
//                    case 7: showDevices(); break;
//                    case 8: turnOnDevice(scanner); break;
//                    case 9: turnOffDevice(scanner); break;
//                    case 10: sortDevices(); break;
//                    case 11: calculateTotalPower(); break;
//                    case 12: findDeviceByInput(scanner); break;
//                    case 101: saveConfig(scanner); break;
//                    case 102: loadConfig(scanner); break;
//                    case 103: startConfig(); break;
//                    case 0: System.out.println("Bye!"); break;
//                    default: System.out.println("Input value should be from 0 to 12 \n"); break;
//                }
//            }
//            catch(Exception ex){
//                System.out.println("Input error " + ex);
//            }
//        }
//        scanner.close();
//    }

    /*
     * This method saves objects to file
     * @param apartment
     * @param ArrayList<Room>
     * @param ArrayList<Socket>
     * @param ArrayList<device>
     * */
    public void saveConfig(String path, File file){
        ObjectReaderWriter objW = new ObjectReaderWriter();
        objW.writeToFile(apartment, path, file);
    }

    /*
     * This method loads objects to file
     * it's also deletes all arrays and fills them again
     * @param scanner that contains file name
     * */
    public void loadConfig(Scanner scanner){
        System.out.println("Enter filename WITH format (name.amogus): \n");
        fileName = scanner.nextLine();
        while(fileName.isEmpty()){
            fileName = scanner.nextLine();
            System.out.println("Name cannot be empty \n");
        }
        try{
            ObjectReaderWriter objR = new ObjectReaderWriter();
            apartment = new Apartment(apartmentName);
            allRooms.clear();
            allSockets.clear();
            allDevices.clear();
            apartment = objR.loadApartmentFromFile(fileName);

            for(Room room: apartment.getRooms()){
                allRooms.add(room);
                allSockets.addAll(room.getSocketList());
                allDevices.addAll(room.getDeviceList());
            }
        } catch (Exception ex) {
            System.out.println("error \n");
        }

    }

    /*
    * Method that adds room into apartment
    * @param scanner that contains room's name
    * */
    public void addRoom(String roomName) {
        Room room = new Room(roomName);
        apartment.addRoomToAppartment(room);
        allRooms.add(room);
        System.out.println("Room " + roomName + " was added.\n");
    }

    /*
    * Method that shows rooms from allRooms arrayList
    * */
    public void showRooms(){
        if(!allRooms.isEmpty()){
            System.out.println("\n Available rooms: ");
            for(Room room: allRooms){
                System.out.println("\t" + room.getRoomName());
            }
        } else{
            System.out.println("No room detected. \n");
        }
    }
    /*
    * Method that adds socket to room
    * @param scanner contains socket name first
    * @param scanner contains room name second
    * */
    public void addSocket(String socketName, String roomName){
        Socket socket = new Socket(socketName,apartment.findRoom(roomName));
        apartment.findRoom(roomName).addSocketToRoom(socket);
        allSockets.add(socket);
        System.out.println("Socket was added to room " + roomName + "\n");
    }

    /*
     * Method that shows sockets from allSockets arrayList
     * */
    public void showSockets(){
        if(!allSockets.isEmpty()){
            for(Socket socket: allSockets){
                System.out.println(socket);
            }
        } else{
            System.out.println("No sockets detected. \n");
        }
    }

    /*
     * Method that adds device to socket
     * @param scanner contains device name first
     * @param scanner contains device power second
     * */
    public void addDevice(String deviceName,int devicePower,String socketName){
        Socket tempSocket = findSocket(socketName);
        Room tempRoom = tempSocket.getRoom();

        try{
            Device device = new Device(deviceName,devicePower,tempSocket,false,tempRoom);
            tempRoom.addDeviceToRoom(device);
            tempSocket.addDeviceToSocket(device);
            allDevices.add(device);
            System.out.println(deviceName + " was added to socket " + socketName + " in room " + tempRoom.getRoomName() + "\n");
        }
        catch (Exception ex){
            System.out.println("Error while adding room" + ex);
        }
    }

    /*
     * Method that shows devices from allDevices arrayList
     * */
    public void showDevices(){
        if(!allDevices.isEmpty()){
            for(Device device : allDevices){
                System.out.println(device);
            }
        } else{
            System.out.println("No device detected. \n");
        }
    }

    /*
     * Method that removes device from memory
     * @param scanner contains device name to delete
     * */
    public void removeDevice(String deviceName){

        Device deviceToDelete;

        //TODO вместо сравнения имён надо сранивать обьекты. но сами обьекты не сравниваюся а сравниваются ссылки?
        // ибо удаляет не то

        deviceToDelete = getDeviceByFullName(deviceName);
        deviceToDelete.getRoom().removeDeviceFromRoom(deviceToDelete);
        deviceToDelete.getSocket().removeDeviceFromSocket(deviceToDelete);
        for(int i = 0; i < allDevices.size(); i++){
            if(allDevices.get(i).getDeviceName().equals(deviceName)){
                allDevices.remove(i);
            }
        }

        System.out.println("\n Device " + deviceToDelete + " was removed. " + "\n");
    }

    /*
     * Method that turns on device
     * @param scanner contains device name to turn on
     * */
    public void turnOnDevice(Scanner scanner){
        if(allDevices.isEmpty() && disabledDeviceAmount() == 0){
            System.out.println("No device detected or all devices are turned on. \n");
            return;
        }

        System.out.println("\n Enter device name. Available devices: ");
        showDisabledDevices();
        String deviceName;
        boolean deviceCondition = false;
        while (!deviceCondition){
            if(allDevices.isEmpty() && disabledDeviceAmount() == 0){
                System.out.println("No device detected or all devices are turned on. \n");
                return;
            }
            deviceName = scanner.nextLine();
            if(getDeviceByFullName(deviceName) == null){
                System.out.println("\n No device found. Available devices: ");
                showDisabledDevices();
            }
            else if (getDeviceByFullName(deviceName).getIsOn()){
                System.out.println("\n Device is already enabled. Available devices: ");
                showDisabledDevices();
            }
            else {
                getDeviceByFullName(deviceName).turnDeviceOn();
                System.out.println("\n Device " + deviceName + " is enabled. " + "\n");
                deviceCondition = true;
            }
        }
    }

    /*
     * Method that turns on device
     * @param scanner contains device name to turn on
     * */
    public void turnOffDevice(Scanner scanner){
        if(allDevices.isEmpty() && enabledDeviceAmount() == 0){
            System.out.println("No device detected or all devices are turned off. \n");
            return;
        }

        System.out.println("\n Enter device name: ");
        showEnabledDevices();
        String deviceName;
        boolean deviceCondition = false;
        while (!deviceCondition){
            if(allDevices.isEmpty() && enabledDeviceAmount() == 0){
                System.out.println("No device detected or all devices are turned off. \n");
                return;
            }
            deviceName = scanner.nextLine();
            if(getDeviceByFullName(deviceName) == null){
                System.out.println("\n No device found. Available devices: ");
                showEnabledDevices();
            }
            else if (!getDeviceByFullName(deviceName).getIsOn()){
                System.out.println("\n Device is already disabled. Available devices: ");
                showEnabledDevices();
            }
            else {
                getDeviceByFullName(deviceName).turnDeviceOff();
                System.out.println("\n Device " + deviceName + " is disabled. " + "\n");
                deviceCondition = true;
            }
        }
    }

    /*
     * Method that sorts devices by power in descend order
     * */
    public void sortDevices(){
        if(!allDevices.isEmpty()){
            ArrayList<Device> sortedDeviceListByPower = allDevices;
            sortedDeviceListByPower.sort(new DevicePowerSorter());
            for(Device device: sortedDeviceListByPower){
                System.out.println(device.getDeviceName() + " " + device.getDevicePower() + " wats.");
            }
        } else{
            System.out.println("Nothing to sort. \n");
        }
    }

    /*
     * Method that calculated total power of enabled devices
     * */
    public int calculateTotalPower(){
        if(!allDevices.isEmpty()){
            int totalPowerOfEnabled = 0;
            for (Device device : allDevices){
                if (device.getIsOn()){ // если включен
                    totalPowerOfEnabled += device.getDevicePower();
                }
            }
            return totalPowerOfEnabled;
        } else {
            return 0;
        }
    }

    /*
     * Method that finds devices by name of part of name
     * @param scanner contains device name
     * @return device
     * */
    public ArrayList<Device> findDeviceByInput(String partOfDeviceName){
        if(!allDevices.isEmpty()){
            ArrayList<Device> list = new ArrayList<>();
            for (Device device : allDevices){
                if (device.getDeviceName().toLowerCase().contains(partOfDeviceName.toLowerCase())
                        || device.getDeviceName().equalsIgnoreCase(partOfDeviceName)){
                    list.add(device);
                }
            }
            return list;
        } else {
            System.out.println("No device detected. \n");
            return null;
        }
    }

    /*
     * Method that finds socket by name
     * @param scanner contains socket name
     * @return socket
     * */
    public Socket findSocket(String name){
        if (allSockets.isEmpty()) {
            System.out.println("No sockets detected. \n");
        } else {
            for (Socket socket : allSockets){
                if (socket.getSocketName().equalsIgnoreCase(name)){
                    return socket;
                }
            }
        }
        return null;
    }

    /*
     * Method that finds room by name
     * @param scanner contains socket name
     * @return socket
     * */
    public Room findRoom(String name){
        if (allRooms.isEmpty()) {
            System.out.println("No rooms detected. \n");
        } else {
            for (Room room : allRooms){
                if (room.getRoomName().equalsIgnoreCase(name)){
                    return room;
                }
            }
        }
        return null;
    }

    /*
     * Method that shows socket in room
     * @param room which sockets we want to see
     * */
    public void showFreeSockets(){
        for(Socket socket: allSockets){
            if(socket.getDevice() == null){
                System.out.println("\t" + socket);
            }
        }
    }

    /*
     * Method that finds device by its FULL name
     * @param String deviceName
     * @return device with deviceName
     * */
    public Device getDeviceByFullName(String deviceName){
        for (Device device : allDevices){
            if(device.getDeviceName().equalsIgnoreCase(deviceName)){
                return device;
            }
        }
        return null;
    }

    /*
     * Method that shows enabled devices
     * @param room which sockets we want to see
     * */
    public void showEnabledDevices(){
        for (Device device : allDevices){
            if (device.getIsOn()){
                System.out.println(device);
            }
        }
    }

    /*
     * Method that shows disabled devices
     * @param room which sockets we want to see
     * */
    public void showDisabledDevices(){
        for (Device device : allDevices){
            if (!device.getIsOn()){
                System.out.println(device);
            }
        }
    }

    /*
     * Method that return amount of enabled devices
     * @return counter
     * */
    public int enabledDeviceAmount(){
        int counter = 0;
        for (Device device : allDevices){
            if (device.getIsOn()){
                counter++;
            }
        }
        return counter;
    }

    /*
     * Method that return amount of disabled devices
     * @return counter
     * */
    public int disabledDeviceAmount(){
        int counter = 0;
        for (Device device : allDevices){
            if (!device.getIsOn()){
                counter++;
            }
        }
        return counter;
    }
}
