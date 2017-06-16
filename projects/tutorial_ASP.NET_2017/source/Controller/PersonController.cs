using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using lab06.Models;

namespace lab06.Models
{
    public class PersonCR
    {
        public string firstname;
        public string lastname;
        public HealthProfile hProfile;
        public string birthdate;
        public long personId;

        public PersonCR(long personId, String fname, String lname, String birthdate, HealthProfile hp)
        {
            this.setPersonId(personId);
            this.setFirstname(fname);
            this.setLastname(lname);
            this.setBirthdate(birthdate);
            this.hProfile = hp;
        }

        public PersonCR(long personId, String fname, String lname, String birthdate)
        {
            this.setPersonId(personId);
            this.setFirstname(fname);
            this.setLastname(lname);
            this.setBirthdate(birthdate);
            this.hProfile = new HealthProfile();
        }

        public PersonCR()
        {
            this.firstname = "Pinco";
            this.lastname = "Pallino";
            HealthProfile hp = new HealthProfile();
            this.hProfile = hp;
            
            // setting personId to a random number between 1 and 9999
            Random rnd = new Random();
            int nro = rnd.Next(9999);
            this.personId = nro;
            
            this.birthdate = "11/11/2011";
        }
        
        public String getFirstname()
        {
            return firstname;
        }
        
        public void setFirstname(String firstname)
        {
            this.firstname = firstname;
        }
        
        public String getLastname()
        {
            return lastname;
        }
        
        public void setLastname(String lastname)
        {
            this.lastname = lastname;
        }
        
        public HealthProfile getHProfile()
        {
            return hProfile;
        }
        
        public void setHProfile(HealthProfile hProfile)
        {
            this.hProfile = hProfile;
        }
        
        public String getBirthdate()
        {
            return birthdate;
        }
        
        public void setBirthdate(String birthdate)
        {
            this.birthdate = birthdate;
        }
        
        public long getPersonId()
        {
            return personId;
        }
        
        public void setPersonId(long personId)
        {
            this.personId = personId;
        }

    }
}
