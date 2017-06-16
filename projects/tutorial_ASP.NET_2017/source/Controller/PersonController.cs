using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using lab06.Models;

namespace lab06.Models
{
    public class PersonController : ApiController
    {
        public PersonCR[] persona = new PersonCR[]
        {
            new PersonCR()
        };

        public IEnumerable<PersonCR> GetAllPersons()
        { 
            return persona;
        };
        
        public IHttpActionResult GetPersonid(int id)
        {
            var pers = persona.FirstOrDefault((p) => p.personId == id);
            if (pers == null)
            {
                return NotFound();
            }
            return Ok(pers);
        };

        public IHttpActionResult Get(long perId)
        {
            var pers = persona.FirstOrDefault((p) => p.personId == perId);
            if (pers == null)
            {
                  return NotFound();
            }
            return Ok(pers.hProfile);
        };

        public void Post(PersonCR pers)
        {
            Array.Resize(ref persona, persona.Length + 1);
            persona[persona.Length-1] = new PersonCR(pers.personId,pers.firstname,pers.lastname,pers.birthdate);
        };

        public void Put(int id, PersonCR perso)
        {
            var pers = persona.FirstOrDefault((p) => p.personId == id);
            if (pers != null)
            {
                pers.firstname = perso.firstname;
                pers.lastname = perso.lastname;
                pers.birthdate = perso.birthdate;
            }
        };
        
        public void Delete(int id)
        {
            Array.Clear(persona, id, 1);
        };
        
    }
}
