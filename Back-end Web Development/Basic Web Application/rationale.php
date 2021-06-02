<?php include "templates/header.php"; ?>

<h2 style="padding: 25px;">Rationale</h2>

<p style="padding: 25px; line-height: 2; font-size: 18px;">

    When starting this project, my first goal was to actually know what it was that I was creating.
    Looking through the list, “university assignment tracker” caught my eye as what could be more relevant
    for this assignment? And for me, that’s about 25% of the work done, i.e., knowing my destination.
    The following 50% is reaching that destination, and the remaining 25% is making sure everything works
    perfectly.<br><br>
    
    The next step for me was to work on how my website was actually going to look. So, I created
    my stylesheet, as well as the php templates for the header and the footer; this way I had my basic
    layout for every page. This was to be my canvas. I also decided that I would be using Bootstrap, as I
    really like Bootstrap and it helps in making good front-end design. I also created the database I would
    be using for this app.<br><br>
    
    Now that I had all that out of the way, it was time to tick off the requirements.
    First things first, I created a page where the user would be able to add the details of their
    assignments to the database in the form of a, well, form (add data to the database). Next, I made a
    page which incorporated read.php, update.php, and delete.php all into one. The reason I did this is for
    the sake of user experience. It doesn’t make sense and is also redundant to have them all in a separate
    page, instead, I decided to just have one page where the data is retrieved, the content is displayed on
    the front-end, and the user can edit and delete the content. This page also tells the user how many days
    are remaining until their assignment is due, which was a little difficult to do as I had to convert the
    dates to timestamps before I could do any subtraction, and convert the result back to days.<br><br>
    
    All that was remaining was the user login and registration stuff, and it hit me: I would have to make it
    so that other users wouldn’t be viewing
    assignments that they haven’t put into the database. I thought on this and realised that I could achieve
    this by keeping the session active on every page and adding a “username” key to the assignments table.
    So, when the user adds an assignment, his username is added to that entry, and the assignments page will
    only select the rows with his username. After that was all done with, I went back to all the pages and
    made sure the layout and padding was perfect according to my standard. Finally, I tested everything to
    make sure it worked perfectly. I’m quite happy with this, and I hope you will be too!

</p>

<?php include "templates/footer.php"; ?>