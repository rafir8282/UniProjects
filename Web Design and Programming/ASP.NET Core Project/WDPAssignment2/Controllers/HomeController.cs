using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using WDPAssignment2.Data;
using WDPAssignment2.Models;
using Microsoft.EntityFrameworkCore;

namespace WDPAssignment2.Controllers
{
    public class HomeController : Controller
    {
        private readonly ApplicationDbContext _context;

        public HomeController(ApplicationDbContext context)
        {
            _context = context;
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        public async Task<IActionResult> MachineLearning()
        {
            if (!User.Identity.IsAuthenticated)
            {
                foreach (var post in _context.MachineLearningCompaniesFeedback)
                {
                    post.canIncreaseLike = true;
                    post.canIncreaseDislike = true;
                }
                await _context.SaveChangesAsync();
            }

            var allDiscussions = from result in _context.MachineLearningCompaniesFeedback
                                 orderby result.PostDate descending
                                 select result;

            return View(await allDiscussions.ToListAsync());
        }

        public IActionResult About()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
