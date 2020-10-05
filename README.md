# Job Board Portal API

This is a simple job portal API build using Spring boot.

Supports following features:-
- Recruiters can use to post jobs that contain (DONE)
  - Company name
  - Job Title
  - Skill keywords
  - Job Description
  - Location

- Users can search for jobs using location and keywords. (TODO)
  - It returns all the jobs in the desired city with matching the keywords sent.
  - User can send three keywords in the search string.
  - Even one keyword matches, it considers it to be a match.
  - The results are sorted from best match to least match.

Also, jobs get expired within 60 days. (This can be specified via POST request)

## Usage
Pull the repo and run the following commands in terminal
```bash
./gradlew bootrun
```
In another terminal tab, run following curl command to test the POST Mapping
```bash
curl -X POST \
  http://localhost:8080/jobs \
  -H 'content-type: application/json' \
  -d '{
	"company": "Paytm",
	"jobTitle": "SDE",
	"location": "Gurugram",
	"description": "Experience with Spring boot and distributed systems",
	"skills": ["Spring boot", "Python", "Java"]
}'
```
