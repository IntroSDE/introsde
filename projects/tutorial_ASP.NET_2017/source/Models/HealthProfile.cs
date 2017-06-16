using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace lab06.Models
{
    public class HealthProfile
    {
        public double weight; // in kg
        public double height; // in m

        public HealthProfile(double weight, double height)
        {
            this.weight = weight;
            this.height = height;
        }

        public HealthProfile()
        {
            this.weight = 85.5;
            this.height = 1.72;
        }

        public void setWeight(double weight)
        {
            this.weight = weight;
        }

        public double getHeight()
        {
            return height;
        }

        public void setHeight(double height)
        {
            this.height = height;
        }

        public double getBMI()
        {
            return this.weight / (Math.Pow(this.height, 2));
        }

        public String toString()
        {
            return "{" + this.height + "," + this.weight + "," + this.getBMI() + "," + "}";
        }


    }
}