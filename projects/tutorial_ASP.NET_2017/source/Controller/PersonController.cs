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
            } ;
        

       

        // GET: api/Person
        public IEnumerable<PersonCR> GetAllPersons()
        {
         
            return persona;
        }

        // GET: api/Person/5
        public IHttpActionResult GetPersonid(int id)
        {
            var pers = persona.FirstOrDefault((p) => p.personId == id);
            if (pers == null)
            {
                return NotFound();
            }
            return Ok(pers);
        }

        [Route("api/person/{perId}/health-profile")]
        public IHttpActionResult Get(long perId)
        {
            var pers = persona.FirstOrDefault((p) => p.personId == perId);


            if (pers == null)
            {
                  return NotFound();

            }

            return Ok(pers.hProfile);
        }

        // POST: api/Person
        /*    public void Post([FromBody]string value)
        {
            PersonCR persona = new PersonCR;
      
        }*/

        // POST: api/Person
           public void Post(PersonCR pers)
        {
            Array.Resize(ref persona, persona.Length + 1);
            persona[persona.Length-1] = new PersonCR(pers.personId,pers.firstname,pers.lastname,pers.birthdate);
           


        }


        // PUT: api/Person/5
        public void Put(int id, PersonCR perso)
        {
            var pers = persona.FirstOrDefault((p) => p.personId == id);


            if (pers == null)
            {
               
            }
            else
            {
                pers.firstname = perso.firstname;
                pers.lastname = perso.lastname;
                pers.birthdate = perso.birthdate;

               
            }
        }

        // DELETE: api/Person/5
        public void Delete(int id)
        {
            Array.Clear(persona, id, 1);
            
        }
    }
}
