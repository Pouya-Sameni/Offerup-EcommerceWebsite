import React from "react";
import clsx from "clsx";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";
import { ContactMail, Payment } from "@material-ui/icons";
import ReceiptIcon from "@mui/icons-material/Receipt";
import LocalShippingIcon from "@mui/icons-material/LocalShipping";

const style = makeStyles((theme) => ({
  root: {
    backgroundColor: "#ccc",
    zIndex: 1,
    color: "#fff",
    width: 50,
    height: 50,
    display: "flex",
    borderRadius: "50%",
    justifyContent: "center",
    alignItems: "center",
  },
  active: {
    background: theme.palette.primary.main,
    boxShadow: "0 4px 10px 0 rgba(0,0,0,.25)",
  },
  completed: {
    background: theme.palette.primary.main,
  },
}));

const PaymentStepIcons = (props) => {
  const classes = style();
  const { active, completed } = props;

  const icons = {
    1: <ContactMail />,
    2: <Payment />,
    3: <LocalShippingIcon />,
    4: <ReceiptIcon />,
  };

  return (
    <div
      className={clsx(classes.root, {
        [classes.active]: active,
        [classes.completed]: completed,
      })}
    >
      {icons[String(props.icon)]}
    </div>
  );
};

PaymentStepIcons.propTypes = {
  active: PropTypes.bool,
  completed: PropTypes.bool,
};

export default PaymentStepIcons;
