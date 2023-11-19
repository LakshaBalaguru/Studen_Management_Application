import java.io.*;
import java.nio.file.*;
import java.util.*;

class Student {
    
    static String full_name;

    static int age;

    static String date_of_birth;

    static String student_class;

    static ArrayList<String> subjects = new ArrayList<String>();

    static ArrayList<Integer> marks = new ArrayList<Integer>();

    static ArrayList<Integer> percentage = new ArrayList<Integer>();

    static ArrayList<String> grade = new ArrayList<String>();

    static Map<String , String> data_mapping = new LinkedHashMap<String , String>();

    static Map<String , Integer> subject_mark_mapping = new LinkedHashMap<String ,Integer>();

    static String file_name = "Student_Data_file.txt";

    static String id_file_name = "id_file.txt";

    static Scanner sc = new Scanner(System.in);

    public static void display_namelist(int choice , List<String> name_list){

        String order = (choice == 1) ? ("Ascending Order") : ("Descending Order");

        System.out.println("\nNames of Students in - " + order + " :\n");

        int serial_no = 1;

        for(String name : name_list){
            System.out.println((serial_no++) + ". " + name);
        }
    }

    public static void filter_namelist(){

        System.out.println("\nDisplay Name List in : ");

        System.out.println("1. Asceding Order");
                
        System.out.println("2. Descending Order");

        System.out.print("\nEnter your choice : ");
        
        int choice = sc.nextInt();
        
        sc.nextLine();

        List<String> name_list = new ArrayList<String>();

        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))){
            
            String current_line;
            
            while((current_line = reader.readLine()) != null){
                
                String[] data = current_line.split(",");

                String[] name_pair = remove_brackets(data[1]).split(":");

                name_list.add(name_pair[1]);
            }
        }catch(Exception e){}

        switch(choice){
            
            case 1:
                Collections.sort(name_list);
                break;
            
            case 2:
                Collections.sort(name_list , Collections.reverseOrder());
                break;
        }

        display_namelist(choice , name_list);
    }

    public static void filter_percentage(){

        System.out.print("\nEnter Percentage : ");

        int percentage_input = sc.nextInt();

        sc.nextLine();

        System.out.println();

        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))){
            
            String current_line;
            
            while((current_line = reader.readLine()) != null){
                
                String[] data = current_line.split(",");

                String[] name_pair = remove_brackets(data[1]).split(":");

                String[] percentage_pair = remove_brackets(data[6]).split(":");

                if(Integer.parseInt(percentage_pair[1]) >= percentage_input){
                    System.out.println("Name : \"" + name_pair[1] + "\"\nPercentage : " + percentage_pair[1] + "\n");
                }
            }
        }catch(Exception e){}
    }

    public static void filter_class(){

        System.out.print("\nEnter Class : ");

        String class_input = sc.nextLine();

        System.out.println();

        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))){
            
            String current_line;
            
            while((current_line = reader.readLine()) != null){
                
                String[] data = current_line.split(",");

                String[] name_pair = remove_brackets(data[1]).split(":");

                String[] class_pair = remove_brackets(data[4]).split(":");

                if(class_input.equals(class_pair[1])){
                    System.out.println("Name : \"" + name_pair[1] + "\"\nClass : " + class_pair[1] + "\n");
                }
            }
        }catch(Exception e){}
    }

    public static void filter_age(){

        System.out.print("\nEnter Age : ");

        int age_input = sc.nextInt();

        sc.nextLine();

        System.out.println();

        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))){
            
            String current_line;
            
            while((current_line = reader.readLine()) != null){
                
                String[] data = current_line.split(",");

                String[] name_pair = remove_brackets(data[1]).split(":");

                String[] age_pair = remove_brackets(data[2]).split(":");

                if(Integer.parseInt(age_pair[1]) >= age_input){
                    System.out.println("Name : \"" + name_pair[1] + "\"\nAge : " + age_pair[1] + "\n");
                }
            }
        }catch(Exception e){}
    }

    public static void filter_based_on_criteria(){

        System.out.println("\nFilter Based On : ");

        System.out.println("1. Age");
        
        System.out.println("2. Class");
        
        System.out.println("3. Percentage");
        
        System.out.println("4. NameList");

        System.out.print("\nEnter your choice : ");
        
        int choice = sc.nextInt();
        
        sc.nextLine();

        switch(choice){
            
            case 1:
                filter_age();
                break;
            
            case 2:
                filter_class();
                break;
            
            case 3:
                filter_percentage();
                break;
            
            case 4:
                filter_namelist();
                break;
        }
    }

    public static void average_marks_of_given_student(){

        System.out.print("\nEnter Student's Full Name : ");

        String student_input = sc.nextLine();

        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))){
            
            String current_line;
            
            while((current_line = reader.readLine()) != null){
                
                String[] data = current_line.split(",");

                String[] name_pair = remove_brackets(data[1]).split(":");

                String[] percentage_pair = remove_brackets(data[6]).split(":");

                if(student_input.equals(name_pair[1])){
                    System.out.println("\nAverage Percentage of Student - \"" + student_input + "\" is : " + percentage_pair[1]);
                    break;
                }
            }
        }catch(Exception e){}
    }

    public static void average_percentage_of_given_class(){

        System.out.print("\nEnter Class : ");

        String class_input = sc.nextLine();

        int total_of_given_class = 0;

        int N = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))){
            
            String current_line;
            
            while((current_line = reader.readLine()) != null){
                
                String[] data = current_line.split(",");

                String[] class_pair = remove_brackets(data[4]).split(":");

                String[] percentage_pair = remove_brackets(data[6]).split(":");

                if(class_input.equals(class_pair[1])){
                    total_of_given_class += Integer.parseInt(percentage_pair[1]);
                    N++;
                }
            }

            System.out.println("\nAverage Percentage of " + class_input + " Class : " + (total_of_given_class / ((double) N)));

        }catch(Exception e){}
    }

    public static void update_id_in_id_file(int last_line_number){

        try(BufferedReader reader = new BufferedReader(new FileReader(id_file_name))){
            last_line_number = Integer.parseInt(reader.readLine());
        }catch(Exception e){}

        last_line_number -= 1;

        // To Update the recent line number after deleting a row of data to generate the new id of the next upcoming data

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(id_file_name))){
            writer.write(last_line_number + "");
        }catch(Exception e){}
    }

    public static void update_balance_rows(int deleted_row_number){

        int last_line_number = (deleted_row_number - 1);

        try{

            List<String>all_lines_of_file = Files.readAllLines(Paths.get(file_name));

            for(String current_line : all_lines_of_file){

                String[] data = current_line.split(",");

                int current_id = Integer.parseInt(data[0]);

                if(current_id > deleted_row_number){
                    
                    current_id -= 1;

                    int index = current_line.indexOf(',');

                    current_line = ((current_id + "") + (current_line.substring(index)));

                    all_lines_of_file.set((current_id - 1) , current_line);

                    last_line_number = current_id;
                }
            }

            if(last_line_number != -999){
                update_id_in_id_file(last_line_number);
            }

            Files.write(Paths.get(file_name) , all_lines_of_file , StandardOpenOption.WRITE , StandardOpenOption.TRUNCATE_EXISTING);

        }catch(Exception e){}
    }

    public static void delete_data(){

        System.out.print("\nEnter Student's Full Name : ");

        String student_name = sc.nextLine();
        
        try{

            List<String>all_lines_of_file = Files.readAllLines(Paths.get(file_name));

            for(String current_line : all_lines_of_file){

                String[] data = current_line.split(",");

                int current_id = Integer.parseInt(data[0]);

                String[] name_pair = remove_brackets(data[1]).split(":");

                if(student_name.equals(name_pair[1])){

                    int line_number = current_id;

                    all_lines_of_file.remove(line_number - 1);

                    Files.write(Paths.get(file_name) , all_lines_of_file , StandardOpenOption.WRITE , StandardOpenOption.TRUNCATE_EXISTING);

                    update_balance_rows(line_number);
                }
            }
        }catch(Exception e){}
    }

    public static void update_data(){
        
        System.out.print("\nEnter Student's Full Name : ");

        String student_name = sc.nextLine();
        
        try{

            List<String>all_lines_of_file = Files.readAllLines(Paths.get(file_name));

            for(String current_line : all_lines_of_file){

                String[] data = current_line.split(",");

                String[] name_pair = remove_brackets(data[1]).split(":");

                if(student_name.equals(name_pair[1])){

                    for(int i=0 , data_length = data.length ; (i < data_length) ; i++){
                        
                        if((i != 0) && (i != 5)){

                            String[] data_split = remove_brackets(data[i]).split(":");

                            data_mapping.put(data_split[0] , data_split[1]);
                        }else if(i == 5){
                            data_mapping.put("Subjects" , data[i]);
                        }
                    }

                    String updated_subject_mark_string = data[5];

                    System.out.println("\nWhich one to Update ?");
                    
                    System.out.println("1. Name");
                    
                    System.out.println("2. Age");
                    
                    System.out.println("3. Date of Birth");
                    
                    System.out.println("4. Class");
                    
                    System.out.println("5. Subjects");

                    System.out.print("\nEnter your choice : ");
        
                    int choice = sc.nextInt();
                    
                    sc.nextLine();

                    switch(choice){

                        case 1:
                            System.out.print("\nEnter Updated Name : ");
                            String new_name = sc.nextLine();
                            data_mapping.put("Name" , new_name);
                            break;
                        
                        case 2:
                            System.out.print("\nEnter Updated Age : ");
                            String new_age = sc.nextLine();
                            data_mapping.put("Age" , new_age);
                            break;
                        
                        case 3:
                            System.out.print("\nEnter Updated Date of Birth : ");
                            String new_dob = sc.nextLine();
                            data_mapping.put("dob" , new_dob);
                            break;
                        
                        case 4:
                            System.out.print("\nEnter Updated Class : ");
                            String new_class = sc.nextLine();
                            data_mapping.put("class" , new_class);
                            break;
                        
                        case 5:

                            updated_subject_mark_string = "";
                            
                            String[] current_subjects = remove_brackets(data[5]).split(" ");

                            for(String subject_mark : current_subjects){

                                String[] subject_mark_pair = subject_mark.split(":");

                                subject_mark_mapping.put(subject_mark_pair[0] , Integer.parseInt(subject_mark_pair[1]));
                            }

                            System.out.println(subject_mark_mapping);

                            System.out.print("\nEnter Subject : ");

                            String subject_input = sc.nextLine();

                            System.out.print("\nEnter Updated Mark : ");

                            int mark_input = Integer.parseInt(sc.nextLine());

                            subject_mark_mapping.put(subject_input , mark_input);

                            for(String key : subject_mark_mapping.keySet()){

                                updated_subject_mark_string += (key + ":" + (subject_mark_mapping.get(key) + "") + " ");
                                
                                marks.add(subject_mark_mapping.get(key));
                            }

                            updated_subject_mark_string = ("[" + updated_subject_mark_string.trim() + "]");

                            int percentage = calculate_percentage();

                            String grade = calculate_grade(percentage);

                            data_mapping.put("Subjects" , updated_subject_mark_string);

                            data_mapping.put("Percentage" , (percentage + ""));

                            data_mapping.put("Grade" , grade);

                            break;
                    }

                    int current_id = Integer.parseInt(data[0] + "");

                    String updated_one_line_data = ((current_id + "") + ",");

                    for(String i : data_mapping.keySet()){

                        String key = i;

                        String value = data_mapping.get(key);

                        if(!(key.equals("Subjects"))){
                            updated_one_line_data += (data_into_pairs(key , value) + ",");
                        }else{
                            updated_one_line_data += (value + ",");
                        }
                    }
                    
                    updated_one_line_data = updated_one_line_data.substring(0 , (updated_one_line_data.length() - 1));

                    int line_number = (current_id - 1);

                    all_lines_of_file.set(line_number , updated_one_line_data);
                    
                    Files.write(Paths.get(file_name) , all_lines_of_file , StandardOpenOption.WRITE , StandardOpenOption.TRUNCATE_EXISTING);
                }
            }
        }catch (Exception e){}
    }

    public static void search_student(){

        System.out.print("\nEnter Student's Full Name : ");

        String student_name = sc.nextLine();

        boolean flag = true;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))){
            
            String current_line;
            
            while((current_line = reader.readLine()) != null){
                
                String[] data = current_line.split(",");

                String[] name_pair = remove_brackets(data[1]).split(":");

                if(student_name.equals(name_pair[1])){
                    flag = false;
                    System.out.println(current_line);
                    break;
                }
            }

        }catch(Exception e){}

        if(flag){
            System.out.println("\nStudent Not Found ... !");
        }
    }

    public static String data_into_pairs(String key , String value){
        
        return ("[" + key + ":" + value + "]");
    }

    public static String remove_brackets(String data){

        return (data.substring(1 , (data.length() -1)));
    }

    public static void show_all_students(){

        System.out.println("\nAll Students Names : \n");

        int serial_no = 1;

        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))){
            
            String current_line;
            
            while((current_line = reader.readLine()) != null){
                
                String[] data = current_line.split(",");

                String[] name_pair = remove_brackets(data[1]).split(":");

                System.out.println((serial_no++) + ". " + name_pair[1]);
            }

        }catch(Exception e){}
    }

    public static int calculate_percentage(){
        
        int total_marks = 0;

        for(Integer i : marks){
            total_marks += i;
        }

        int N = marks.size();

        return (total_marks / N);
    }

    public static String calculate_grade(int percentage){

        if(percentage >= 90){
            return "A+";
        }else if(percentage >= 80){
            return "A";
        }else if(percentage >= 70) {
            return "B";
        }else if(percentage >= 60) {
            return "C";
        }else if(percentage >= 50) {
            return "D";
        }else if(percentage < 50){
            return "F";
        }

        return "";
    }

    public static String make_data_one_line(String subject_and_mark){

        subject_and_mark = subject_and_mark.replace(","," ");

        int percentage = calculate_percentage();

        String grade = calculate_grade(percentage);

        String one_line_data = data_into_pairs("Name" , full_name) + "," + data_into_pairs("Age" , (age + "")) + "," + data_into_pairs("dob" , date_of_birth) + "," + data_into_pairs("class" , student_class) + ",[" + subject_and_mark + "]," + data_into_pairs("Percentage" , (percentage + "")) + "," + data_into_pairs("Grade" , (grade + ""));

        return one_line_data;
    }

    public static void data_entry_function(){

        int last_line_number = -999;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(id_file_name))){
            last_line_number = Integer.parseInt(reader.readLine());
        }catch(Exception e){}

        System.out.println("\nEnter The Following Data :\n");
        
        System.out.print("FullName : ");

        full_name = sc.nextLine();

        System.out.print("Age : ");

        age = Integer.parseInt(sc.nextLine());

        System.out.print("Date Of Birth {dd/mm/yyyy} : ");

        date_of_birth = sc.nextLine();

        System.out.print("Class : ");

        student_class = sc.nextLine();

        System.out.print("Subject : Mark (Comma Seperated) : ");

        String subject_and_mark = sc.nextLine();

        for(String subject_and_mark_string : subject_and_mark.split(",")){
            
            String[] string_array = subject_and_mark_string.split(":");

            subjects.add(string_array[0]);

            marks.add(Integer.parseInt(string_array[1]));
        }

        int id = (last_line_number + 1);

        String one_line_data = make_data_one_line(subject_and_mark);

        String file_data_string = ((id + "") + "," + one_line_data);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file_name , true))){
            writer.write(file_data_string);
            writer.newLine();
        }catch(Exception e){}

        // To Update the recent line number to generate the new id of the next upcoming data

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(id_file_name))){
            writer.write(id + "");
        }catch(Exception e){}
    }

    public static void main(String[] args){

        while(true){

            System.out.println("\nStudent Management System");
            
            System.out.println("1. Enter Students Data");
            
            System.out.println("2. Show All Students");
            
            System.out.println("3. Filter Students based on Criteria");
            
            System.out.println("4. Search for a Student");
            
            System.out.println("5. Update a Student's Record");
            
            System.out.println("6. Delete a Student");
            
            System.out.println("7. Get Average Percentage of a Class");
            
            System.out.println("8. Calculate Average Marks of a Student");
            
            System.out.println("9. Exit");
    
            System.out.print("\nEnter your choice : ");
    
            int choice = sc.nextInt();
            
            sc.nextLine();
    
            switch(choice){

                case 1:
                    data_entry_function();
                    break;
                
                case 2:
                    show_all_students();
                    break;
                
                case 3:
                    filter_based_on_criteria();
                    break;
                
                case 4:
                    search_student();
                    break;
                
                case 5:
                    update_data();
                    break;
                
                case 6:
                    delete_data();
                    break;
                
                case 7:
                    average_percentage_of_given_class();
                    break;
                
                case 8:
                    average_marks_of_given_student();
                    break;
                
                case 9:
                    System.out.println("Exiting Student Management System ... !");
                    System.exit(0);
                
                default:
                    System.out.println("Choose A Valid Option ... !");
            }
        }
    }
}