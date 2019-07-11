# GWAS Deposition App Backend
*(Work in progress)*

## Known gaps
 * Summary stats file status listing part of the FileUpload objects

## Publication endpoints

### Search publication by PMID
`GET /v1/publications?pmid=<pmid>`
 * Param:
   * `pmid` - the PMID used for searching
 * Response:
 ```
  {
    "pmid": "qCwgXmVFfl",
    "title": "buk0KeIoNz",
    "journal": "kzJpT9n4oU",
    "authors": [
      "NcXZeGpeYL",
      "kB1lCUxj2h",
      "ZVG4JvrKkj"
    ],
    "publicationDate": "2019-07-11T21:16:28.719+08:00",
    "correspondingAuthor": {
      "authorName": "2hScpthcLZ",
      "email": "bNVlDqVFSP"
    },
    "status": "ELIGIBLE | CURATION_STARTED"
  } 
 ```

### Retrieve publication by ID
`GET /v1/publications/{publicationId}`
 * Response:
 ```
  {
    "pmid": "qCwgXmVFfl",
    "title": "buk0KeIoNz",
    "journal": "kzJpT9n4oU",
    "authors": [
      "NcXZeGpeYL",
      "kB1lCUxj2h",
      "ZVG4JvrKkj"
    ],
    "publicationDate": "2019-07-11T21:16:28.719+08:00",
    "correspondingAuthor": {
      "authorName": "2hScpthcLZ",
      "email": "bNVlDqVFSP"
    },
    "status": "ELIGIBLE | CURATION_STARTED"
  } 
 ```

## Submission endpoints

### Create submission
`POST /v1/submissions`
 * Payload:
```
  {
    "publication": {
      "pmid": "GT9UtzGAVy",
      "title": "gEP6hj6ujw",
      "journal": "9tmhdOrcZ0",
      "authors": [
        "SS1GLUBn6a",
        "MnJb8DDTSR",
        "Qxi7h3kmUy"
      ],
      "publicationDate": "2019-07-11T21:25:11.487+08:00",
      "correspondingAuthor": {
        "authorName": "yzQIQaf1Is",
        "email": "jRMmbQYqWH"
      },
      "status": "ELIGIBLE"
    }
  }
```
 * Response:
```
  {
    "id": "5d2738b78fbea3185724f80b",
    "publication": {
      "pmid": "GT9UtzGAVy",
      "title": "gEP6hj6ujw",
      "journal": "9tmhdOrcZ0",
      "authors": [
        "SS1GLUBn6a",
        "MnJb8DDTSR",
        "Qxi7h3kmUy"
      ],
      "publicationDate": "2019-07-11T21:25:11.487+08:00",
      "correspondingAuthor": {
        "authorName": "yzQIQaf1Is",
        "email": "jRMmbQYqWH"
      },
      "status": "ELIGIBLE"
    },
    "status": "STARTED |
                VALIDATING_METADATA |
                INVALID_METADATA |
                VALID_METADATA |
                VALIDATING_SUMMARY_STATS |
                INVALID_SUMMARY_STATS |
                VALID_SUMMARY_STATS |
                VALID |
                SUBMITTED |
                EXPORTED"
    "created": {
      "user": {
        "name": "kSDeQ4i3wF",
        "email": "hEeqX3Pt04"
      },
      "timestamp": "2019-07-11T21:25:11.757+08:00"
    }
  }
```

### Retrieve submission by ID
`GET /v1/submissions/{submissionId}`
 * Response:
```
  {
    "id": "5d2739c48fbea318fd812362",
    "publication": {
      "pmid": "nldU7o176v",
      "title": "eHqlsyODVj",
      "journal": "CzqeV2vMWx",
      "authors": [
        "oUaSIcohxh",
        "zQ2WFdoVke",
        "4rkPB1TMwv"
      ],
      "publicationDate": "2019-07-11T21:29:39.683+08:00",
      "correspondingAuthor": {
        "authorName": "dfmv5RKnys",
        "email": "IuyAsYifX5"
      },
      "status": "ELIGIBLE"
    },
    "status": "STARTED",
    "files": [],
    "studies": [
      {
        "study_tag": "SCPgtfCjNJ",
        "genotyping_technology": "OHKKJmFpFn",
        "array_manufacturer": "CKQ815dEBM",
        "array_information": "2KafH4tmCW",
        "imputation": false,
        "variant_count": 10,
        "statistical_model": "ppdt8b4nA5",
        "study_description": "OMXcoMHpA6",
        "trait": "c7bBOOEgRn",
        "efo_trait": "yK0ahrFb3v",
        "background_trait": "xhPaaHE8Ly",
        "background_efo_trait": "tm7B5O1RGd",
        "summary_statistics_file": "zBLLcmRpSn",
        "summary_statistics_assembly": "5YWLfllNP8"
      }
    ],
    "samples": [
      {
        "study_tag": "GzI1akVyOK",
        "size": 10,
        "cases": 10,
        "controls": 10,
        "sample_description": "P1Yf5Uazuo",
        "ancestry_category": "X3XwNrErYC",
        "ancestry": "sF7Et6dYd6",
        "ancestry_description": "3FIwvM1Ee0",
        "country_recruitement": "TRHfrm1y8p",
        "stage": "gKCsJpdXdK"
      }
    ],
    "associations": [
      {
        "study_tag": "ENN94WnJLV",
        "haplotype_id": "NFUGDZSNNX",
        "variant_id": "Y3E4VyP8N1",
        "pvalue": 10,
        "pvalue_text": "ztaYS8TBiL",
        "proxy_variant": "jaoDvApSyM",
        "effect_allele": "a5tmP7wDNo",
        "other_allele": "0IBD6njYmn",
        "effect_allele_frequency": 10,
        "odds_ratio": 10,
        "beta": 10,
        "beta_unit": "7s3R9Lfght",
        "ci_lower": 10,
        "ci_upper": 10,
        "standard_error": 10
      }
    ],
    "notes": [
      {
        "study_tag": "5dk6J5bECJ",
        "note": "HbZR8blH4f",
        "note_subject": "g2F3vgLo2m",
        "status": "l6HefmXjFi"
      }
    ],
    "created": {
      "user": {
        "name": "Bv4txqbUP8",
        "email": "W0MD2qCEA0"
      },
      "timestamp": "2019-07-11T21:29:40.012+08:00"
    }
  }
```

### Retrieve all submissions (paginated)
`GET /v1/submissions`
 * Params:
   * `page` - start page
   * `itemsPerPage` - number of items to be received per page
   * `_sort` - sorting option: `asc` | `desc`
 * Response:
```
  {
    "metadata": {
      "page": 1,
      "itemsPerPage": 10,
      "totalPages": 1,
      "totalItems": 1,
      "filters": {}
    },
    "results": [
      {
        "id": "5d2739818fbea318b4f8d6b7",
        "publication": {
          "pmid": "t0MBhLX0FM",
          "title": "GRC3kf3EpT",
          "journal": "GQAjOHQvYZ",
          "authors": [
            "wwPPJ1bCkx",
            "tWq7dCDTMO",
            "VcFYPAOzTk"
          ],
          "publicationDate": "2019-07-11T21:28:33.247+08:00",
          "correspondingAuthor": {
            "authorName": "iDQZIDe6jj",
            "email": "XKHD11f0CM"
          },
          "status": "ELIGIBLE"
        },
        "status": "STARTED",
        "files": [],
        "studies": [
          {
            "study_tag": "Y9MYiOdWvf",
            "genotyping_technology": "iGijQ0QuSK",
            "array_manufacturer": "ZJaBQQWd15",
            "array_information": "Ty8hi29GCG",
            "imputation": false,
            "variant_count": 10,
            "statistical_model": "47g83cq0ie",
            "study_description": "gVDoOkPOvU",
            "trait": "ccW91w9k2R",
            "efo_trait": "8AXTZET94d",
            "background_trait": "pfx25wJgmi",
            "background_efo_trait": "1aEDnxaUZN",
            "summary_statistics_file": "Q3P8gHrpKm",
            "summary_statistics_assembly": "U3d3KcvGbl"
          }
        ],
        "samples": [
          {
            "study_tag": "92m4M4tYbB",
            "size": 10,
            "cases": 10,
            "controls": 10,
            "sample_description": "XlmbDT54Tg",
            "ancestry_category": "kHYu47Ooa9",
            "ancestry": "UaAdM0vu92",
            "ancestry_description": "4Qye5m7Zld",
            "country_recruitement": "3yfJdZcl6Y",
            "stage": "WgY2zmq8PD"
          }
        ],
        "associations": [
          {
            "study_tag": "TVUJSHa9WZ",
            "haplotype_id": "sGBNvDBnb7",
            "variant_id": "lktgHdC8f8",
            "pvalue": 10,
            "pvalue_text": "OxgGEr5SJY",
            "proxy_variant": "hD5BlWBr3x",
            "effect_allele": "Mzt9faEn9P",
            "other_allele": "OB7YM9p9sq",
            "effect_allele_frequency": 10,
            "odds_ratio": 10,
            "beta": 10,
            "beta_unit": "0XeJaMQ9mm",
            "ci_lower": 10,
            "ci_upper": 10,
            "standard_error": 10
          }
        ],
        "notes": [
          {
            "study_tag": "JFpT3pTJNf",
            "note": "16i6TQgOwL",
            "note_subject": "iOJHC49uvO",
            "status": "QyzCS9DGOF"
          }
        ],
        "created": {
          "user": {
            "name": "qyE7fM1sBt",
            "email": "6O4siALsIb"
          },
          "timestamp": "2019-07-11T21:28:33.548+08:00"
        }
      }
    ]
  }
```

### Submit submission
`PUT /v1/submissions/{submissionId}/submit`
 * Response:
```
  {
    "id": "5d2739c48fbea318fd812362",
    "publication": {
      "pmid": "nldU7o176v",
      "title": "eHqlsyODVj",
      "journal": "CzqeV2vMWx",
      "authors": [
        "oUaSIcohxh",
        "zQ2WFdoVke",
        "4rkPB1TMwv"
      ],
      "publicationDate": "2019-07-11T21:29:39.683+08:00",
      "correspondingAuthor": {
        "authorName": "dfmv5RKnys",
        "email": "IuyAsYifX5"
      },
      "status": "ELIGIBLE"
    },
    "status": "STARTED",
    "files": [],
    "studies": [
      {
        "study_tag": "SCPgtfCjNJ",
        "genotyping_technology": "OHKKJmFpFn",
        "array_manufacturer": "CKQ815dEBM",
        "array_information": "2KafH4tmCW",
        "imputation": false,
        "variant_count": 10,
        "statistical_model": "ppdt8b4nA5",
        "study_description": "OMXcoMHpA6",
        "trait": "c7bBOOEgRn",
        "efo_trait": "yK0ahrFb3v",
        "background_trait": "xhPaaHE8Ly",
        "background_efo_trait": "tm7B5O1RGd",
        "summary_statistics_file": "zBLLcmRpSn",
        "summary_statistics_assembly": "5YWLfllNP8"
      }
    ],
    "samples": [
      {
        "study_tag": "GzI1akVyOK",
        "size": 10,
        "cases": 10,
        "controls": 10,
        "sample_description": "P1Yf5Uazuo",
        "ancestry_category": "X3XwNrErYC",
        "ancestry": "sF7Et6dYd6",
        "ancestry_description": "3FIwvM1Ee0",
        "country_recruitement": "TRHfrm1y8p",
        "stage": "gKCsJpdXdK"
      }
    ],
    "associations": [
      {
        "study_tag": "ENN94WnJLV",
        "haplotype_id": "NFUGDZSNNX",
        "variant_id": "Y3E4VyP8N1",
        "pvalue": 10,
        "pvalue_text": "ztaYS8TBiL",
        "proxy_variant": "jaoDvApSyM",
        "effect_allele": "a5tmP7wDNo",
        "other_allele": "0IBD6njYmn",
        "effect_allele_frequency": 10,
        "odds_ratio": 10,
        "beta": 10,
        "beta_unit": "7s3R9Lfght",
        "ci_lower": 10,
        "ci_upper": 10,
        "standard_error": 10
      }
    ],
    "notes": [
      {
        "study_tag": "5dk6J5bECJ",
        "note": "HbZR8blH4f",
        "note_subject": "g2F3vgLo2m",
        "status": "l6HefmXjFi"
      }
    ],
    "created": {
      "user": {
        "name": "Bv4txqbUP8",
        "email": "W0MD2qCEA0"
      },
      "timestamp": "2019-07-11T21:29:40.012+08:00"
    }
  }
```

### Delete submission (soft delete for now)
`DELETE /v1/submissions/{submissionId}`
 * Response: `HTTP.200`

## File upload endpoints

### Create file upload
`POST /v1/submissions/{submissionId}/uploads`
 * Param:
   * `file`: `Multipart` file payload
 * Response: 
```
  {
    "id": "5d273a758fbea3197d239a2b",
    "status": "PROCESSING | VALIDATING | FINALISED",
    "fileName": "file",
    "fileSize": 33343
  }
```

### Retrieve file upload by ID
`GET /v1/submissions/{submissionId}/uploads/{fileUploadId}`
 * Response: 
```
  {
    "id": "5d273a758fbea3197d239a2b",
    "status": "PROCESSING | VALIDATING | FINALISED",
    "fileName": "file",
    "fileSize": 33343
  }
```

### Retrieve all file uploads for a submission
`GET /v1/submissions/{submissionId}/uploads`
 * Response: 
```
  [
    {
      "id": "5d273a758fbea3197d239a2b",
      "status": "PROCESSING | VALIDATING | FINALISED",
      "fileName": "file",
      "fileSize": 33343
    }
  ]
```

### Download file
`GET /v1/submissions/{submissionId}/uploads/{fileUploadId}/download`
 * Response: 
   * `byte[]` - file content
   * `Content-Disposition` Header set to `attachment; filename=<file name>`

### Delete file upload
`DELETE /v1/submissions/{submissionId}/uploads/{fileUploadId}`
 * Response: `HTTP.200`
