# GWAS Deposition App Backend

## Publication endpoints

### Search publication by PMID
`GET /v1/publications?pmid=<pmid>`
 * Param:
   * `pmid` - the PMID used for searching
 * Response:

### Retrieve publication by ID
`GET /v1/publications/{publicationId}`
 * Response:

## Submission endpoints

### Create submission
`POST /v1/submissions`
 * Payload:
 * Response:

### Retrieve submission by ID
`GET /v1/submissions/{submissionId}`
 * Response:

### Retrieve all submissions (paginated)
`GET /v1/submissions`
 * Params:
   * `page` - start page
   * `itemsPerPage` - number of items to be received per page
   * `_sort` - sorting option: `asc` | `desc`

### Submit submission
`PUT /v1/submissions/{submissionId}/submit`
 * Payload:
 * Response:

### Delete submission (soft delete for now)
`DELETE /v1/submissions/{submissionId}`
 * Response: `HTTP.200`

## File upload endpoints

### Create file upload
`POST /v1/submissions/{submissionId}/uploads`
 * Payload: 
 * Response: 

### Retrieve file upload by ID
`GET /v1/submissions/{submissionId}/uploads/{fileUploadId}`
 * Response: 

### Retrieve all file uploads for a submission
`GET /v1/submissions/{submissionId}/uploads`
 * Response: 

### Download file
`GET /v1/submissions/{submissionId}/uploads/{fileUploadId}/download`
 * Response: 

### Delete file upload
`DELETE /v1/submissions/{submissionId}/uploads/{fileUploadId}`
 * Response: `HTTP.200`


