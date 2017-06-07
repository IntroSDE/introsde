using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Text;
using Nancy;
using Nancy.ModelBinding;


namespace WebApplication1
{

public class MainModule : NancyModule
    {
        private static List<Person> persons = new List<Person>()
        {
            new Person() { Name = "Kierkegaard", Lastname = "Shaw", Birthdate = "Inflated", Weight = 85.5,  Height= 1.72},
            new Person() { Name = "Kierkegaard2", Lastname = "Shaw2", Birthdate = "Inflated2", Weight = 85.5,  Height= 1.72}
        };


    public MainModule()
        {
            Get["/person"] = Personlist;

            Get["/person/{id}"] =  PersonId;

            Get["/person/{id}/health-profile"] = PersonHealth;
            
            Post["/person"] = PostPerson;

            Put["/person/{id}"] = PutPerson;

            Delete["/person/{id}"] = DeletePerson;
            
        }
        // GET return the list of people in the database, with their health profiles, which should include at least weight and height.
        private dynamic Personlist(dynamic parameters) 
        {
            foreach (var item in persons)
                item.Bmi = item.Weight / (item.Height * item.Height);
  
            return Response.AsJson(persons);
        }
        // GET returns the record for person with id 'personId'
        private dynamic PersonId(dynamic parameters) 
        {
            int id = (parameters.id - 1);
            try
            {
                List<HealthP> results = new List<HealthP>() {
                    new HealthP() { Name = persons.ElementAt(id).Name, Lastname = persons.ElementAt(id).Lastname, Birthdate = persons.ElementAt(id).Birthdate}
                };

                return Response.AsJson(results);
            }
            catch (Exception)
            {
                return "no exite la persona";
               // throw;
            }



        }
        // GET returns the health-profile of 'personId'
        private dynamic PersonHealth(dynamic parameters) 
        {
            int id = (parameters.id - 1);
            foreach (var item in persons)
                item.Bmi = item.Weight / (item.Height * item.Height);

            try { 
                return Response.AsJson(persons.ElementAt(id));
            }
           catch{ return "no exite la persona"; }

        }
        //POST creates a new person in the system
        private dynamic PostPerson(dynamic parameters) 
        {
            var model = this.Bind<Person>();
            persons.Add(model);
            return Response.AsJson(persons);
        }
        //PUT update information about the person
        private dynamic PutPerson(dynamic parameters) 
        {
            int id = (parameters.id - 1);
            try
            { 
                var model = this.Bind<Person>();
                persons.RemoveAt(id);
                persons.Insert(id, model);
                return Response.AsJson(persons);
            }
            catch { return "no exite la persona"; }
        }
        private dynamic DeletePerson(dynamic parameters) //DELETE deletes a person and her health profile
        {
            int id = (parameters.id - 1);
            try
            {
                var itemToRemove = persons.ElementAt(id);
                persons.Remove(itemToRemove);
                return Response.AsJson(persons);
            }
            catch
            {
                return "no se puedo eliminar persona";
            }
        }


    }
}
