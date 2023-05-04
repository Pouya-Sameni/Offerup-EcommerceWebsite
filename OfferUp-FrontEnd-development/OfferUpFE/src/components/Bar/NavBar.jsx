import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Container from "@mui/material/Container";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";

import favicon from "../../assets/favicon.png";

function ResponsiveAppBar() {
  const navigate = useNavigate();

  const handleNavigateToHome = () => {
    navigate("/home");
  };

  const handleNavigateToAbout = () => {
    navigate("/about");
  };

  const handleNavigateToSignIn = () => {
    navigate("/");
  };

  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleAgree = () => {
    handleClose();
    Cookies.remove("sessionToken");
    navigate("/");
  };

  return (
    <AppBar position="static" sx={{ backgroundColor: "#2c3030" }}>
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Box sx={{ mr: 2 }}>
            <img
              src={favicon}
              alt="Shop"
              style={{
                "@media (min-width: 768px)": {
                  display: "flex",
                  marginRight: 1,
                },
              }}
            />
          </Box>
          <Box sx={{ display: "flex", alignItems: "center", flexGrow: 1 }}>
            <Button
              color="inherit"
              onClick={handleNavigateToHome}
              sx={{
                fontFamily: "poppins",
                fontWeight: 700,
                letterSpacing: ".3rem",
                color: "inherit",
                textDecoration: "none",
                marginRight: 1,
              }}
            >
              Home
            </Button>
            <Button
              color="inherit"
              onClick={handleNavigateToAbout}
              sx={{
                fontFamily: "poppins",
                fontWeight: 700,
                letterSpacing: ".3rem",
                color: "inherit",
                textDecoration: "none",
                marginRight: 1,
              }}
            >
              About
            </Button>
            <Button
              color="inherit"
              onClick={handleClickOpen}
              sx={{
                fontFamily: "poppins",
                fontWeight: 700,
                letterSpacing: ".3rem",
                color: "inherit",
                textDecoration: "none",
                marginRight: 1,
              }}
            >
              Logout
            </Button>
            <Dialog
              open={open}
              onClose={handleClose}
              aria-labelledby="alert-dialog-title"
              aria-describedby="alert-dialog-description"
            >
              <DialogTitle id="alert-dialog-title" sx={{ textAlign: "center" }}>
                {"Log Out"}
              </DialogTitle>
              <DialogContent>
                <DialogContentText
                  id="alert-dialog-description"
                  sx={{ textAlign: "center" }}
                >
                  You will be returned to the login screen.
                </DialogContentText>
              </DialogContent>
              <DialogActions sx={{ display: "flex", justifyContent: "center" }}>
                <Button onClick={handleClose}>Disagree</Button>
                <Button onClick={handleAgree}>Agree</Button>
              </DialogActions>
            </Dialog>
            <div className="text-white">
              <p
                style={{
                  position: "absolute",
                  fontFamily: "poppins",
                  fontSize: "20px",
                  top: "30%",
                  left: "90%",
                  flex: "flex-end",
                }}
              >
                OfferUp!
              </p>
            </div>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
}

export default ResponsiveAppBar;
