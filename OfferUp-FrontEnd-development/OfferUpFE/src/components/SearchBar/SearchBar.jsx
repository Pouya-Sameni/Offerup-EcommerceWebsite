import * as React from "react";
import Paper from "@mui/material/Paper";
import InputBase from "@mui/material/InputBase";
import IconButton from "@mui/material/IconButton";
import SearchIcon from "@mui/icons-material/Search";
import { useState } from "react";
import { searchForProduct } from "../../services/Catalogue";
import { useNavigate, useLocation } from "react-router-dom";
import { Typography } from "@mui/material";
import { Button } from "@mui/material";

export default function SearchBar() {
  const [itemDescription, setItemDescription] = useState("");
  const [searchMessage, setSearchMessage] = useState("");

  const navigate = useNavigate();

  const handleViewAll = () => {
    navigate("/items", { state: " " });
  };
  const handleSearch = () => {

    if (itemDescription) {
      navigate("/items", { state: itemDescription });
    } else {
      setSearchMessage(
        <div>
          Sorry, we could not find any items matching your search. <br />
          Please try a different search term.
        </div>
      );
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    handleSearch();
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Paper
          component="div"
          sx={{
            p: "2px 4px",
            display: "flex",
            alignItems: "center",
            width: 500,
            backgroundColor: "#bdceff",
          }}
        >
          <InputBase
            className="ml-1 flex-1"
            style= {{fontSize: "18px",textAlign: "center", fontFamily: "poppins",fontWeight: "bold"}}
            placeholder="What Are You Looking For?"
            inputProps={{ "aria-label": " what are you looking for" }}
            value={itemDescription}
            onChange={(event) => setItemDescription(event.target.value)}
          />
          <IconButton
            sx={{ p: "20px" }}
            aria-label="menu"
            onClick={handleSearch}
          >
            <SearchIcon />
          </IconButton>
        </Paper>
      </form>
      {searchMessage && (
        <Typography variant="body2" color="red" style={{ fontSize: "18px",textAlign: "center", fontFamily: "poppins",fontWeight: "inherit", marginTop: 30}}>
          {searchMessage}
        </Typography>
      )}
            <div
        style={{
          position: "absolute",
          top: "110%",
          left: "0%",
        }}
      >
        <Button
          type="submit"
          variant="contained"
          sx={{
            mb: 100,
            fontSize: "12px",
            fontFamily: "poppins",
            fontWeight: "bold",
            padding: "20px 10px",
            height: "30px",
            backgroundColor: "pink",
            color: "#ffffff",
            width: "100%",
          }}
          onClick={handleViewAll}
        >
          View All
        </Button>
      </div>
    </div>
    
  );
}
