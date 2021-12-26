package com.jerseycrud;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("students")
public class StudentResource {
    StudentRepository s = new StudentRepository();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents()
    {
//        Student s1 = new Student(200970119,"Psk","28-09-1998","25-12-2021");
//        Student s2 = new Student(200970120,"Sony","29-09-1998","24-12-2021");
        return s.getStudents();
    }
    @GET
    @Path("student/{s_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("s_id") int id)
    {
        return s.getStudent(id);
    }
    @POST
    @Path("create")
    public Student insertStudent(Student s1)
    {
        s.insertStudent(s1);
        return s1;
    }
    @PUT
    @Path("update")
    public Student updateStudent(Student s1)
    {
        if(s.getStudent(s1.getS_id()).getS_id() == 0)
        {
            s.insertStudent(s1);
        }
        else
        {
            s.updateStudent(s1);
        }
        return s1;
    }
    @DELETE
    @Path("delete/{s_id}")
    public Student deleteStudent(@PathParam("s_id") int id)
    {
        Student s1 = s.getStudent(id);
        if(s1.getS_id() != 0)
        {
            s.delete(id);
        }

        return s1;
    }

}
