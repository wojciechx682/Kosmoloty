import java.io.*;
import java.util.ArrayList; // globalna tablica imion
import java.util.Set;
import java.util.HashSet;

public class Kosmoloty
{
    public static boolean is_alpha_numeric(String s)
    {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean is_numeric(String s)
    {
        return s != null && s.matches("-?[0-9]\\d*");
    }

    public static boolean is_numeric_arg(String s)
    {
        return s != null && s.matches("-?[1-9]\\d*");
    }

    public static boolean proper_length(String s)
    {
        if(s.length()>10)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static boolean is_empty(String s)
    {
        if (s == null || s.equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static ArrayList<String> names = new ArrayList<String>();
    public static ArrayList<String> x = new ArrayList<String>();
    public static ArrayList<String> y = new ArrayList<String>();
    public static ArrayList<String> Vx = new ArrayList<String>();
    public static ArrayList<String> Vy = new ArrayList<String>();
    public static int lines = 0;
    static boolean validate_name(String name)
    {
        if(!is_alpha_numeric(name) || !proper_length(name) || !is_empty(name))
        {
            return false;
        }       

        names.add(name);

        return true;
    }

    static boolean duplicate_names(ArrayList<String> names)
    {
        Set<String> s = new HashSet<String>();

        for(String name : names)
        {
            if(!s.add(name))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean validate_speed(String vx_s)
    {
        int vx = Integer.parseInt(vx_s);

        if((vx < -10000) || (vx > 10000))
        {
            return false;
        }
        return true;
    }

    public static boolean validate_position(String x_s, int wielkosc_torusa)
    {
        int x_i = Integer.parseInt(x_s);

        if((x_i < 0) || (x_i > wielkosc_torusa-1))
        {
            return false;
        }
        return true;
    }

    public static boolean validate_args(String[] args)
    {
        int t_x = Integer.parseInt(args[0]);
        int t_y = Integer.parseInt(args[1]);

        if((!(t_x > 0 && t_x <= 100000)) || (!(t_y > 0 && t_y <= 100000)))
        {
            return false;
        }      

        return true;
    }

    public static boolean has_duplicates_in_rows(int[][] inArray)
    {
        for (int row = 0; row < inArray.length; row++)
        {
            for (int col = 0; col < 1; col++)
            {
                int c1 = inArray[row][col];
                int c2 = inArray[row][col+1];

                if(row+1 <= inArray.length)
                {
                    for (int otherRow = row + 1; otherRow < inArray.length; otherRow++)
                    {
                        if ((c1 == inArray[otherRow][col]) && (c2 == inArray[otherRow][col+1]) && (inArray[otherRow][col] != -1))
                        {
                            return true;
                        }
                    }
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean has_duplicates_in_rows(int[] inArray)
    {
        for (int row = 0; row < inArray.length; row++)
        {
            int c1 = inArray[row];

            if(row+1 <= inArray.length)
            {
                for (int otherRow = row + 1; otherRow < inArray.length; otherRow++)
                {
                    if ((c1 == inArray[otherRow]) && (inArray[otherRow] != -1))
                    {
                        return true;
                    }
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public static ArrayList<String> indexes_of_duplicated_rows(int[][] inArray, int row_indeks)
    {
        ArrayList<String> a = new ArrayList<String>();

        for (int row = row_indeks; row < inArray.length; row++)
        {
            for (int col = 0; col < 1; col++)
            {
                int c1 = inArray[row][col];
                int c2 = inArray[row][col+1];

                if(row+1 <= inArray.length)
                {
                    for (int otherRow = row + 1; otherRow < inArray.length; otherRow++)
                    {
                        if ((c1 == inArray[otherRow][col]) && (c2 == inArray[otherRow][col+1]) && (inArray[otherRow][col] != -1))
                        {
                            a.add("true");
                            a.add(Integer.toString(row));
                            a.add(Integer.toString(otherRow));
                            return a;
                        }
                    }
                }
                else
                {
                    a.add("false");
                    return a;
                }
            }
        }
        a.add("false");
        return a;
    }

    public static ArrayList<String> removeDuplicates(ArrayList<String> indeksy)
    {
        ArrayList<String> removed_duplicates = new ArrayList<String>();

        for (String element : indeksy)
        {
            if (!removed_duplicates.contains(element))
            {
                removed_duplicates.add(element);
            }
        }

        return removed_duplicates;
    }

    public static int get_index_of_largest(int[] array)
    {
        if (array == null || array.length == 0)
        {
            return -1;
        }

        int largest = 0;

        for (int i = 1; i < array.length; i++)
        {
            if (array[i] > array[largest])
            {
                largest = i;
            }
        }

        return largest;
    }


    public static int main(String[] args, String filename)
    {
        boolean correct_input = true;

        if((args[0].equals("-1")) || (args[1].equals("-1")) || (filename.equals("-1")))
        {            
            System.out.println("klops");
            return 1;
        }
        else
        {
            if(!(is_numeric_arg(args[0])) || !(is_numeric_arg(args[1])))
            {                
                System.out.println("klops");
                return 1;
            }
        }

        int wielkosc_torusa_x = Integer.parseInt(args[0]);
        int wielkosc_torusa_y = Integer.parseInt(args[1]);

        if(!validate_args(args))
        {
            correct_input = false;
        }

        if(!correct_input)
        {
            System.out.println("klops");
            return 1;
        }

        try
        {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;

            while((line = br.readLine())!=null)
            {
                lines++;
                sb.append(line);
                sb.append("\n");
                String[] parts = line.split(",");

                if(!validate_name(parts[0]))
                {
                    correct_input = false;
                }

                if((!is_numeric(parts[1])) || (!is_numeric(parts[2])) || (!is_numeric(parts[3])) || (!is_numeric(parts[4])))
                {                  
                    System.out.println("klops");
                    return 1;
                }

                if((!validate_speed(parts[1])) || (!validate_speed(parts[2])) || (!(validate_position(parts[3], wielkosc_torusa_x))) || (!(validate_position(parts[4], wielkosc_torusa_y))))
                {
                    correct_input = false;
                }         

                if(!correct_input)
                {
                    System.out.println("klops");
                    return 1;
                }

                x.add(parts[3]);
                y.add(parts[4]);

                Vx.add(parts[1]);
                Vy.add(parts[2]);
            }

            if((lines == 0) || (!duplicate_names(names)))
            {
                correct_input = false;
            }

            if(!correct_input)
            {
                System.out.println("klops");
                return 1;
            }

            fr.close();
        }

        catch(IOException e)
        {
            System.out.println("klops");
            return 1;
        }

        int[][] xy_positions = new int[lines][2];
        int[][] VxVy_moves = new int[lines][2];

        int[] distances = new int[lines];

        for (int row = 0; row < xy_positions.length; row++)
        {
            for (int col = 0; col < xy_positions[row].length; col++)
            {
                if(col == 0)
                {
                    xy_positions[row][col] = Integer.parseInt(x.get(row));
                    VxVy_moves[row][col] = Integer.parseInt(Vx.get(row));
                }
                if(col == 1)
                {
                    xy_positions[row][col] = Integer.parseInt(y.get(row));
                    VxVy_moves[row][col] = Integer.parseInt(Vy.get(row));
                }
            }
        }

        if(has_duplicates_in_rows(xy_positions))
        {
            System.out.println("klops");
            return 1;
        }

        ArrayList<String> indeksy = new ArrayList<String>();
        ArrayList<String> removed_rows;

        boolean wszystkie_zniszczone = true;

        for (int i = 0; i < 86400; i++)
        {
            for (int row = 0; row < xy_positions.length; row++)
            {
                for (int col = 0; col < xy_positions[row].length; col++)
                {
                    if(xy_positions[row][col] != -1)
                    {
                        if(col == 0)
                        {
                            xy_positions[row][col] = xy_positions[row][col] + VxVy_moves[row][0];
                        }
                        if(col == 1)
                        {
                            xy_positions[row][col] = xy_positions[row][col] + VxVy_moves[row][1];
                        }

                        while(xy_positions[row][0] < 0)
                        {
                            xy_positions[row][0] = wielkosc_torusa_x + xy_positions[row][0];
                        }

                        while(xy_positions[row][1] < 0)
                        {
                            xy_positions[row][1] = wielkosc_torusa_y + xy_positions[row][1];
                        }

                        while(xy_positions[row][0] > wielkosc_torusa_x-1)
                        {
                            xy_positions[row][0] = Math.abs(xy_positions[row][0] - wielkosc_torusa_x);
                        }
                        while(xy_positions[row][1] > wielkosc_torusa_y-1)
                        {
                            xy_positions[row][1] = Math.abs(xy_positions[row][1] - wielkosc_torusa_y);
                        }

                        if(col == 0)
                        {
                            distances[row] = distances[row] + (Math.abs(VxVy_moves[row][0]) + Math.abs(VxVy_moves[row][1]));
                        }
                    }
                }
            }

            if(has_duplicates_in_rows(xy_positions))
            {
                wszystkie_zniszczone = true;

                ArrayList<String> result = indexes_of_duplicated_rows(xy_positions, 0);

                int k = 0;

                while(result.get(0) != "false")
                {
                    indeksy.add(result.get(1));
                    indeksy.add(result.get(2));

                    result = indexes_of_duplicated_rows(xy_positions, Integer.parseInt(indeksy.get(k)+1));

                    k++;
                }

                removed_rows = removeDuplicates(indeksy);

                for (int ii = 0; ii < removed_rows.size(); ii++)
                {
                    xy_positions[Integer.parseInt(removed_rows.get(ii))][0] = -1;
                    xy_positions[Integer.parseInt(removed_rows.get(ii))][1] = -1;
                    distances[Integer.parseInt(removed_rows.get(ii))] = -1;
                }

                for (int row = 0; row < xy_positions.length; row++)
                {
                    if (xy_positions[row][0] != -1)
                    {
                        wszystkie_zniszczone = false;
                        break;
                    }
                }

                if(wszystkie_zniszczone)
                {
                    break;
                }
            }
            else
            {
                wszystkie_zniszczone = true;

                for (int row = 0; row < xy_positions.length; row++)
                {
                    if (xy_positions[row][0] != -1)
                    {
                        wszystkie_zniszczone = false;
                        break;
                    }
                }

                if(wszystkie_zniszczone)
                {
                    break;
                }
            }
        }

        if(has_duplicates_in_rows(distances))
        {
            System.out.println("remis");
        }
        else
        {
            int winner_index = get_index_of_largest(distances);

            System.out.println(names.get(winner_index));
        }
        return 0;
    }

    public static void main(String args[])
    {
        Kosmoloty obj = new Kosmoloty();

        if((args.length == 0) || (args.length == 1) || (args.length == 2) )
        {
            String[] error = {"-1", "-1"};
            int result = obj.main(error, "-1");
        }
        else
        {
            String filename = args[2];
            int result = obj.main(args, filename);
        }
    }
}
