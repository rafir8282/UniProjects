using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace WDPAssignment2.Models
{
    public class MachineLearningCompaniesFeedback
    {
        // ID
        public int Id { get; set; }

        // Date
        [Display(Name = "Post Date")]
        public DateTime PostDate { get; set; }

        // Username
        [Display(Name = "User Name")]
        public string UserName { get; set; }

        // Company Name
        [Required]
        [Display(Name = "Company Name")]
        public string TopicTitle { get; set; }

        // Heading
        [Display(Name = "Heading")]
        public string Heading { get; set; }

        // Feedback Content
        [Required]
        [Display(Name = "Feedback Content")]
        public string MessageContent { get; set; }

        // Agree
        public int Like { get; set; }

        // Disagree
        public int Dislike { get; set; }

        // Star Rating
        public int StarRating { get; set; }

        public bool canIncreaseLike { get; set; }

        public bool canIncreaseDislike { get; set; }
    }
}
