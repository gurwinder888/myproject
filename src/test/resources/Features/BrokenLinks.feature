Feature: Check Login fuctionality


   @CheckBrokenLinks
   Scenario: Check Broken Links
   Given Open browser and navigate to website "https://www.infosys.com"
   When Broken link
   Then Test Status
   