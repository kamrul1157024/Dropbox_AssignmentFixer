import javax.swing.*;
import java.io.File;
import java.util.ArrayList;


public class FileRenamer {


    ArrayList<String> haveFolder=new ArrayList<>();


    boolean isNum(char c)
    {
        return c>='0'&&c<='9';
    }

    char charat(String str,int i)
    {
        int len=str.length();
        if(i>0&&i<len)
            return str.charAt(i-1);
        else if(i>=0&&i<len-1)
            return str.charAt(i+1);
        return ' ';
    }

    boolean isRoll(String str)
    {
        int len=str.length();
        System.out.println("in roll checker"+str+str.indexOf("1510"));
        // System.out.println(str.indexOf("2015100"));
        if (str.indexOf("1510")!=-1)
            return true;
        return false;
    }

    boolean isZip(File file)
    {
        String str=file.getName();
        if(str.indexOf("zip")!=-1||str.indexOf("rar")!=-1||str.indexOf("tar")!=-1||str.indexOf("tar.gz")!=-1)
            return true;
        else
            return false;
    }

    void report(String str)
    {
        System.out.println(str+ " this file is wrong");
    }

    String fixFileName(String str)
    {

        if(isRoll(str))
        {
            if(str.indexOf("2015")==0)
                return str.substring(2);
            else
                return str;
        }
        else
            report(str);

        return " ";
    }



    String getRoll(String name)
    {
        int cnt=0;
        int namelen=name.length();
        for(int i=0;i<namelen;i++)
        {
            if(isNum(charat(name,i))&&(isNum(charat(name,i))||isNum(charat(name,i))))
                cnt++;
            else
                break;
        }
        return name.substring(0,cnt);
    }





    boolean rename(File now)
    {
        String absolutePath=now.getAbsolutePath();
        File oldname=new File(absolutePath);
        String name=now.getName();
        int namelen=name.length();
        String fileType=name.substring(namelen-4);

        String nameSubstr=getRoll(name);
        nameSubstr=fixFileName(nameSubstr);
        //  System.out.println(nameSubstr);
        int abspathlen=absolutePath.length();
        StringBuffer strbuff=new StringBuffer();
        strbuff.append(absolutePath.substring(0,abspathlen-namelen));
        strbuff.append(nameSubstr);
        if (isZip(now))
            strbuff.append(fileType);
        // System.out.println(strbuff.toString());
        //System.out.println(name.substring(0,cnt));
        File newname=new File(strbuff.toString());

        return oldname.renameTo(newname);
    }


    void fileFolderRename(File now)
    {
        boolean isFileRenamed=rename(now);
        if (isFileRenamed)
            System.out.println("rename successfull ");
        else
            System.out.println("there is some error");
    }


    void getAll(File f,String space)
    {
        if(f.isDirectory())
        {
            String []list=f.list();
            for(String s:list)
            {
                s=f.getAbsolutePath()+'/'+s;
                s=s.replace('\\', '/');
                //System.out.println(s);
                File now=new File(s);
                String roll=fixFileName(getRoll(now.getName()));
                System.out.println(roll);
                if(!now.equals(null)){
                    if(now.isDirectory())
                    {

                        haveFolder.add(roll);

                        fileFolderRename(now);

                        System.out.println(space+"->"+now.getName());
                        getAll(now,space+" 	");


                    }
                    else
                    {


                        if (isZip(now)&&haveFolder.indexOf(roll)==-1)
                            fileFolderRename(now);
                        System.out.println(space+"-"+now.getName());
                    }

                }
            }
        }
        return;
    }


    void listClear()
    {
        haveFolder.clear();
    }


    FileRenamer(String dir)
    {


        System.out.println("current dir = " + dir);
        File f=new File(dir);
        getAll(f," ");
        listClear();
        System.out.println("Listed all");
    }

}