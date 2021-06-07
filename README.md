# HobbyRoute

This is a repository created to store and proceed with my Hobbyroute project created from July to December 2020.


# Project outline

 A solution to provide customized hobby content applying recommendation system and place & pathfinding algorithm, 'Hobbyroute' is a project that recommends customized hobbies services and contents, and presents relevant places that exist within the radius of user activity with an optimal route.
 
 Hobby-Route uses a personalized algorithm that uses a recommendation system that hybridizes CF(Collaborative Filtering) and CBF(Content-Based Filtering). This recommendation system is an algorithm adopted by various large-scale video platforms such as YouTube and Netflix as a way to increase the time spent by users. Personalized place recommendation considering time and space applies a route search algorithm using Hadoop Mapreduce and Google Map. In addition to the hobby service and the best recommendation for that place, Hobbyroute provides additional functions to enhance the convenience and utility of users.
 
 The measurement of the project's performance is done for each algorithm unit. The recommendation system measures accuracy using indicators such as TPR (True Positive Rate) and FPR (False Positive Rate), and creates a ROC Curve(Receiver Operating Curve) to make it easier to see the performance level. And I conducted a recommendation system survey in Google form to users who actually participated in the project. The number of actual users can be checked through Google Analytics. The best place suggestion algorithm directly tests a certain amount of paths or more, evaluates the recommended distance and optimality, and creates a comparison table with a system that provides similar functions.
